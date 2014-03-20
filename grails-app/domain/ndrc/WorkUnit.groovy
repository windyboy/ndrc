package ndrc

class WorkUnit {

	// Integer workUnitsID
	String workUnitsName

	static mapping = {
		table 'WorkUnits'
		id column:'WorkUnitsID' ,generator:'assigned'
		version false
		workUnitsName column:'WorkUnitsName'
	}

    static constraints = {
    }

    String toString() {
    	workUnitsName
    }
}
