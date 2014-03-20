package ndrc

class QueryStatFilters {

    def stat

    def filters = {
        project (controller:'project', action:'*') {
            before = {
                if (actionName.equals('show')) {
                    stat.plusOne('project_show')
                }
                else if (actionName.equals('query')) {
                    stat.plusOne('project_query')
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }

        apply (controller:'apply', action:'*') {
            before = {
                if (actionName.equals('query')) {
                    stat.plusOne('apply_query')
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }


    
}
