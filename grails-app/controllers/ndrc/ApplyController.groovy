package ndrc

// import grails.rest.RestfulController
// import grails.transaction.*
// import static org.springframework.http.HttpStatus.*
// import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.sql.Sql

class ApplyController {
	def dataSource_mysql
	static def CHINESE_COL = [
		'project_name', 'organ', 'type', 'address', 'having_sys_type',
		'main_build','product_name','main_product_ability','is_agree',
		'dept_name','dept_type', 'link_man', 'fund_source_dis', 
		'project_shareholder'
	]
	def index () {

	}

	def query() {
		println "query apply"
		def result
		def applys = []
		if (params.name == null) {
			result = '{ error: "name can not be null"}'
        }
        else {
            def sql = new Sql(dataSource_mysql)
            //println "sql = $sql"
            String applySQL = """
            select 
            	uid, 
            	project_name, is_reject, is_cancel, is_cancel_date, 
            	organ, apply_org, address, having_sys_type, type,
            	scope, code, build_area, land_area, main_build, product_name,
            	main_product_ability, total_invest, tojian_invest, self_money,
            	rent_money, piao_money, community_money,poeple_money, out_money,
            	other_money, begin_date, over_date, submit_date, fund_source_dis,
            	chinese_invest, outer_invest, link_man, phone, mail_zip, suggestion,
            	proof_no, proof_flow_no, proof_memo, copy_email, copy_man
            	apply_type, max_proof_no, is_remove, apply_no, is_agree, need_query,
            	print_accept_opinion, proof_have_date, equipment_invest, 
            	year_invest1, year_invest2, year_invest3, year_invest4, year_invest5, 
            	year_invest6, year_invest7, handle_dept, finance_invest, city_invest,
            	town_invest, create_by_ent_user, dept_name, dept_type, accept_result,
            	is_upload, tudi_invest, is_synchronisation, project_shareholder, 
            	user_organ_code, link_man_email, total_invest_dollar, classification,
            	link_address, update_remark, sllsh, apply_address_id, mobile, 
            	exchange_step, serial_no
            from  
            	t_apply
            where 
            	is_reject = 0
				and is_cancel is not null
				and is_remove = 0
				and max_proof_no <> 0
            	and project_name like ?
            limit 0, 10;
            """
            // applySQL = new String(applySQL.getBytes("UTF-8"), "ISO-8859-1")
            // String v = new String('广州花花世界购物中心'.getBytes("GBK"), "ISO-8859-1")
            String name = "%${params.name}%"
            // name = new String(name.getBytes(), "ISO-8859-1")
            // println name
            // applySQL = new String(applySQL.getBytes("UTF-8"),"ISO-8859-1")
            // println applySQL
            // pvalue = new String(pvalue.getBytes("UTF-8"), "ISO-8859-1")
            sql.execute "set names 'latin1';"
            log.info "name: $name"
            sql.eachRow(applySQL,[name]) {
            // sql.eachRow(applySQL) {
            	def a = it.toRowResult()
            	a = decode(a, CHINESE_COL)
            	applys << a
            }

            result = applys as JSON
		}
		render result
	}

	private def decode(def source, def values) {
		if (source != null && values != null) {
			values.each{
				source[it] = getEncodedString(source.get(it))
			}
			source
		}
	}

	private String getEncodedString(String source) {
		String target = source
		// println source.getBytes()
		// String target = new String(source.getBytes("ISO-8859-1"), "UTF-8")
		if (source!=null) target = new String(source.getBytes("ISO-8859-1"), "GBK")
		// println new String(target.getBytes(), "GBK")
		target
	}
}