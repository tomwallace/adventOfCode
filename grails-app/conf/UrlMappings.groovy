class UrlMappings {

	static mappings = {

        "/incoming/endPoint/**" {
            controller = 'incoming'
            action = 'endPoint'
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
