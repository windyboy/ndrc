package ndrc

// import grails.rest.RestfulController
// import grails.transaction.*
// import static org.springframework.http.HttpStatus.*
// import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.sql.Sql


// class ProjectController extends RestfulController {
class ProjectController {

    def dataSource

    def stat

	// static responseFormats = ['json', 'xml']
	// ProjectController() {
	// 	super(Project)
	// }

    def index() { 

    }

    def show() {
        // def project = Project.findByProjectCode(params.id)
        print params
        def sql = new Sql(dataSource)
        // def projects = []
        def project
        String projectSql = """
Select
    top 1   
    a.ProjectID as ProjectID ,                  
    ProjectCode,
    ProjectName,
    ProjectType,
    ProjectContent ,
    WorkDate ,
    FinishDate ,
    StartYear ,
    EndYear ,
    WorkUnitsID ,
    ProjectPrincipal ,
    ContactAddress ,
    ContactTel  , 
    ProjectAddress ,
    DeclarerID ,
    DeclarTime ,
    ManageUnitsName ,
          (select WorkUnitsName 
            from WorkUnits 
            where 
            WorkUnitsID=a.DirectorUnitsID) as WorkUnitsName,
          b.PJInvestSum as PJInvestSum
    from 
        PJBaseInfo a,
        ProjectInvest b
    where 
        ProjectType=1 --暂时只查询新开工项目
        and b.ProjectID=a.ProjectID 
        and ProjectCode=?
"""

// println projectSql

        def docString = """
select 
   PJAdjunctid ,
   PJAdjunctType ,
   FileName  ,
   RecorderTime 
from PJAdjunct
where 
ObjectId=?
and (PJAdjunctType = 0 or PJAdjunctType = 5)
        """

        sql.eachRow(projectSql, [params.id]) { it ->
            project = it.toRowResult()
            // println p.ProjectID
            def docs = []
            def dsql = new Sql(dataSource)
            dsql.eachRow(docString, [project.ProjectID]) { doc ->
                docs << doc.toRowResult()
            }
            project['Documents'] = docs
            p.ContactTel = "" //电话留空
            project.remove('ProjectID')
        }
        render project as JSON
    }


    def query() {
        
        def projects = []
        if (params.name == null) {
            result = '{ error: "name can not be null"}'
        }
        else {
            def sql = new Sql(dataSource)
        
//         String projectSql = """
// Select 
//     top 10  
//     a.ProjectID as ProjectID ,                  
//     ProjectCode,
//     ProjectName,
//     ProjectType,
//     ProjectContent ,
//     WorkDate ,
//     FinishDate ,
//     StartYear ,
//     EndYear ,
//     WorkUnitsID ,
//     ProjectPrincipal ,
//     ContactAddress ,
//     ContactTel  , 
//     ProjectAddress ,
//     DeclarerID ,
//     DeclarTime ,
//     ManageUnitsName ,
//           (select WorkUnitsName 
//             from WorkUnits 
//             where 
//             WorkUnitsID=a.DirectorUnitsID) as WorkUnitsName,
//           b.PJInvestSum as PJInvestSum
//     from 
//         PJBaseInfo a,
//         ProjectInvest b
//     where 
//         ProjectType=1 --暂时只查询新开工项目
//         and b.ProjectID=a.ProjectID 
//         and ProjectName like ?
// """

String projectSql = """
select top 10 * from
(select distinct                  
    ProjectCode,
    ProjectID,
    ProjectName,
    ProjectType,
    ProjectContent ,
    WorkDate ,
    FinishDate ,
    StartYear ,
    EndYear ,
    WorkUnitsID ,
    ProjectPrincipal ,
    ContactAddress ,
    ContactTel  , 
    ProjectAddress ,
    DeclarerID ,
    DeclarTime ,
    ManageUnitsName ,
    WorkUnitsName ,
    PJInvestSum
from (
        Select ProjectID,                                                         
          ProjectCode,
          ProjectName,
          ProjectType,
          ProjectContent,
          WorkDate,
          FinishDate,
          StartYear,
          EndYear,
          WorkUnitsID,
          ProjectPrincipal,
          ContactAddress,
          ContactTel, 
          ProjectAddress,
          DeclarerID,
          DeclarTime,
          ManageUnitsName,
          (select WorkUnitsName from WorkUnits where WorkUnitsID=a.DirectorUnitsID) as WorkUnitsName,
          (select PJInvestSum from ProjectInvest where ProjectID=a.ProjectID) as PJInvestSum
        from 
           PJBaseInfo a 
        where 
           ProjectType=1 and ProjectName like ? 
    ) as Temp
) as tProject
"""

String pname = "%$params.name%"
// println projectSql

        def docString = """
select 
   PJAdjunctid ,
   PJAdjunctType ,
   FileName  ,
   RecorderTime 
from PJAdjunct
where ObjectId=? 
and (PJAdjunctType = 0 or PJAdjunctType = 5)
        """

        sql.eachRow(projectSql, [pname]) { it ->
            def p = it.toRowResult()
            // println p.ProjectID
            def docs = []
            def dsql = new Sql(dataSource)
            dsql.eachRow(docString, [p.ProjectID]) { doc ->
                docs << doc.toRowResult()
            }
            p['Documents'] = docs
            p.remove('ProjectID')
            p.ContactTel = "" //电话留空
            projects << p
        }
        }

        render projects as JSON
    }

    def download() {
        println params
        if (params.id != null) {
            PJAdjunct doc = PJAdjunct.findByPJAdjunctid(params.id)
            if (doc != null) {
                // println "$doc.fileName $doc.fileUrl"
                int p = doc.fileUrl.lastIndexOf('\\')
                String name = doc.fileUrl.substring(p+1)
                println doc.fileName
                String fileName = new String(doc.fileName.getBytes("UTF-8"), "ISO8859-1");
                println fileName
                String baseDir = "/Users/windy/Downloads/GZProjectApplyWeb/GZProjectApplyWeb2012/UploadFile"
                String url = "$baseDir/$name"
                println url
                def file = new File(url)
                if (file.exists()) {
                    response.setContentType("application/octet-stream")
                    response.setHeader("Content-disposition", "filename=\"${fileName}\"")
                    response.outputStream << file.bytes
                    return
                }
                else {
                    render "{error:'file not found'}"
                }
            }
        }
    }

    def status() {
        println stat
        render "{stat:$stat.statistics, startTime:$stat.startTime}"
    }

}
