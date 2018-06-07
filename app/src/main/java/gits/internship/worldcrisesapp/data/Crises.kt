package gits.internship.worldcrisesapp.data

data class Crises(
        var _id : String? = null,
        var created_at : String? = null,
        var crisis_alertLevel : String? = null,
        var crisis_eventid : String? = null,
        var crisis_population : String? = null,
        var crisis_population_hash : Population? = null,
        var crisis_relatedGDACSResources : MutableList<Resource>? = null,
        var crisis_severity : String? = null,
        var crisis_severity_hash : Severity? = null,
        var crisis_vulnerability : String? = null,
        var crisis_vulnerability_hash : Vulnerability? = null,
        var data_schema_version : String? = null,
        var dc_date : String? = null,
        var dc_description : String? = null,
        var dc_subject : MutableList<String>? = null,
        var dc_title : String? = null,
        var dct_modified : String? = null,
        var deleted_at : String? = null,
        var foaf_based_near : MutableList<Double>? = null,
        var gn_parentCountry : MutableList<String>? = null,
        var rdfs_seeAlso : String? = null,
        var schema_endDate : String? = null,
        var schema_startDate : String? = null,
        var updated_at : String? = null
)
data class Severity(
        var value : Double? = 0.0,
        var unit : String? = null
)

data class Vulnerability(
        var value : Int? = 0
)

data class Population(
        var value : Int? = 0,
        var unit : String? = null
)

data class Resource(
        var _id : String? = null,
        var dc_description : String? = null,
        var dc_format : String? = null,
        var dc_source : String? = null,
        var dc_title : String? = null,
        var dc_type : String? = null,
        var rdf_resource : String? = null
)