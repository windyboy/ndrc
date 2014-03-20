package ndrc

class PJAdjunct {

    // Integer   pJAdjunctid		//附件标识
    Integer pJAdjunctid
    String   pJAdjunctType		//附件类型
    String fileName				//附件名称
    String fileUrl   			//附件链接
    Date recorderTime			//上传时间

    static constraints = {
    }

    static mapping = {
    	table 'PJAdjunct'
    	version false
    	id column:'ObjectId',  generator:'assigned'
        pJAdjunctid column:'pJAdjunctId'
    	pJAdjunctType column:'PJAdjunctType'
    	fileName column:'FileName'
    	fileUrl column:'FileUrl'
    	recorderTime column:'RecorderTime'
    }
}
