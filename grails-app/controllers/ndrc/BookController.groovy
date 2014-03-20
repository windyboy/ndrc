package ndrc
import grails.rest.RestfulController

class BookController extends RestfulController{

	static responseFormats = ['json', 'xml']
    BookController() {
        super(Book)
    }

    def index() { }

    // @Override
    // Book queryForResource(Serializable id) {
    //     Book.where {
    //         id == id && author.id = params.authorId
    //     }.find()
    // }
}
