class UrlMappings {

	static mappings = {

		"/projects/$id" (controller:"project", method:"GET", action:"show") 
        "/projects/query"(controller:"project", method:"GET", action: "query")
        "/projects/status"(controller:"project", method:"GET", action: "status")
        "/projects/download/$id"(controller:"project", method:"GET", action: "download")

        "/apply/query"(controller:"apply", method:"GET", action:"query")


		"/books"(resources:"book")

        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
