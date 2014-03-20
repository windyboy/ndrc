import ndrc.Book
import ndrc.Project
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
    	JSON.registerObjectMarshaller(Project) {
		  def project= [:]
		  project['projectCode'] = it.projectCode
		  project['projectName'] = it.projectName
		  if (it.projectType == 1) project['projectType'] = '新开工项目'
		  else if (it.projectType == 2) project['projectType'] = '续建项目'
		  else if (it.projectType == 3) project['projectType'] = '预备项目'
		  //project['projectType'] = it.projectType
		  project['projectContent'] = it.projectContent
		  project['workDate'] = it.workDate
		  project['finishDate'] = it.finishDate
		  project['startYear'] = it.startYear
		  project['endYear'] = it.endYear
		  project['workUnitsID'] = it.workUnitsID
		  project['projectPrincipal'] = it.projectPrincipal
		  project['projectAddress'] = it.projectAddress
		  project['contactAddress'] = it.contactAddress
		  project['contactTel'] = it.contactTel
		  project['declarTime'] = it.declarTime
		  project['manageUnitsName'] = it.manageUnitsName
		  // println it.directorUnit
		  project['directorUnit'] = it.directorUnit.workUnitsName
		  // project['pjStatus'] = it.pjStatus
		  // project['directorUnitsName'] = it.directorUnitsName
		  //println it.pJAdjunct
		  // if (it.pJAdjunct){

		  // 		project['pJAdjunctType'] = it.pJAdjunct.pJAdjunctType
			 //  	project['fileUrl'] = it.pJAdjunct.fileUrl
			 //  	project['fileName'] = it.pJAdjunct.fileName
			 //  	// project['recorderID'] = it.recorderID
			 //  	project['recorderTime'] = it.pJAdjunct.recorderTime

		  // }
		  

		  return project 
		}


        //new Project(code:"abc123", type:"t1", name:"n1").save()
        //new Project(code:"abc111", type:"t2", name:"n2").save()
        //print Project.list()

        //new Book(title:"The Stand").save()
        //new Book(title:"The Shining").save()
        //print Book.list()
    }
    def destroy = {
    }
}
