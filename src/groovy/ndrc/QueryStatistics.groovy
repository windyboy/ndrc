class QueryStatistics {
	static Date startTime = new Date()

    def statistics = [
    	"project_show" : 0,
    	"project_query" : 0,
    	"apply_query" : 0
    ]


    def plusOne(String name) {
    	if (statistics[name]) statistics[name]++
    }
}