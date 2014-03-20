package ndrc

class Project {

    Integer projectID           //ID

    String projectCode          //项目编码
    
    String projectName          //项目名称

    Integer projectType          //项目类型
    
    String projectContent       //主要建设内容及规模

    Date workDate             //开工日期或计划开工日期

    Date finishDate             //计划竣工日期
    
    Integer startYear           //建设开始年份 int 例如："2013"
                                              
    Integer endYear             //建设结束年份 int 例如："2014"
    
    String workUnitsID          //项目业主
    
    String projectPrincipal     //项目负责人(项目联系人)
    
    String contactAddress       //联系地址

    String contactTel           //联系电话 

    String projectAddress       //项目所在地 例如："广州天河区燕岭路394号银河烈士陵园内"
                                              
    String declarerID           //申报人   例如："张三"
    
    Date declarTime           //申报日期
    
    String manageUnitsName      //项目建设管理（代建）单位
    
    // Integer directorUnitsID    //主管单位
    WorkUnit directorUnit           //主管单位

    PJAdjunct pJAdjunct             //附件


    // Integer pjStatus                //项目状态 int {0,1,2,3,7}
    
    // String pJAdjunctType        //附件类型
    
    // String fileUrl              //附件链接
    
    // String fileName             //附件名称
    
    // String recorderID           //用户ID 
    
    // Date recorderTime           //上传时间

    static constraints = {
	
        pJAdjunct  (nullable:true,unique:true)
        directorUnit (nullable:true,unique:true)
    }

    static mapping = {
        table 'PJBaseInfo'
        id name: 'projectID', column:'ProjectID', generator:'assigned'
    	version false
        projectCode column:'ProjectCode'
        projectName column:'ProjectName'
        projectType column:'ProjectType'
        projectContent column:'ProjectContent'
        workDate column:'workDate'
        finishDate column:'finishDate'
        startYear column:'startYear'
        endYear column:'endYear'
        workUnitsID column:'WorkUnitsID'
        projectPrincipal column:'ProjectPrincipal'
        projectAddress column:'projectAddress'
        contactAddress column:'ContactAddress'
        contactTel column:'contactTel'
        declarerID column:'declarerID'
        declarTime column:'DeclarTime'
        manageUnitsName column:'ManageUnitsName'
        directorUnit column:'directorUnitsID'
        pJAdjunct column:'ProjectID'
        // directorUnitsID column:'DirectorUnitsID'
        // directorUnit column:'DirectorUnitsID'

        // pjStatus column:'pjStatus'
        // directorUnitsName column:'DirectorUnitsName', type:'int'
        // pJAdjunctType column:'PJAdjunctType'
        // fileUrl column:'FileUrl'
        // fileName column:'FileName'
        // recorderID column:'RecorderID'
        // recorderTime column:'RecorderTime'
    	//code index: 'idx_code'
    }
}
