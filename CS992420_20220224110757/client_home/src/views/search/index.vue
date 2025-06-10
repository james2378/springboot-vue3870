<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="宠物百科"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/doctor/list', 'get')"
				:list="result_doctor_doctor_job_number"
				title="医生医生工号"
				source_table="doctor"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/doctor_introduction/list', 'get')"
				:list="result_doctor_introduction_name_of_doctor"
				title="医生介绍医生姓名"
				source_table="doctor_introduction"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/doctor_introduction/list', 'get')"
				:list="result_doctor_introduction_department"
				title="医生介绍科室"
				source_table="doctor_introduction"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/have_an_appointment_with_a_doctor/list', 'get')"
				:list="result_have_an_appointment_with_a_doctor_order_number"
				title="预约挂号订单号"
				source_table="have_an_appointment_with_a_doctor"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/have_an_appointment_with_a_doctor/list', 'get')"
				:list="result_have_an_appointment_with_a_doctor_name_of_doctor"
				title="预约挂号医生姓名"
				source_table="have_an_appointment_with_a_doctor"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/have_an_appointment_with_a_doctor/list', 'get')"
				:list="result_have_an_appointment_with_a_doctor_department"
				title="预约挂号科室"
				source_table="have_an_appointment_with_a_doctor"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/medical_record_/list', 'get')"
				:list="result_medical_record__name_of_doctor"
				title="病历记录医生姓名"
				source_table="medical_record_"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/medical_record_/list', 'get')"
				:list="result_medical_record__department"
				title="病历记录科室"
				source_table="medical_record_"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/medical_apparatus_and_instruments/list', 'get')"
				:list="result_medical_apparatus_and_instruments_device_number"
				title="医疗器械器械编号"
				source_table="medical_apparatus_and_instruments"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/medical_apparatus_and_instruments/list', 'get')"
				:list="result_medical_apparatus_and_instruments_purchase_date"
				title="医疗器械购入日期"
				source_table="medical_apparatus_and_instruments"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/medical_apparatus_and_instruments/list', 'get')"
				:list="result_medical_apparatus_and_instruments_state"
				title="医疗器械状态"
				source_table="medical_apparatus_and_instruments"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_consultation/list', 'get')"
				:list="result_online_consultation_name_of_doctor"
				title="在线咨询医生姓名"
				source_table="online_consultation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_consultation/list', 'get')"
				:list="result_online_consultation_department"
				title="在线咨询科室"
				source_table="online_consultation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/maintenance_report/list', 'get')"
				:list="result_maintenance_report_device_name"
				title="维修上报器械名称"
				source_table="maintenance_report"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_registered_user_user_name":[],
			"result_doctor_doctor_job_number":[],
			"result_doctor_introduction_name_of_doctor":[],
			"result_doctor_introduction_department":[],
			"result_have_an_appointment_with_a_doctor_order_number":[],
			"result_have_an_appointment_with_a_doctor_name_of_doctor":[],
			"result_have_an_appointment_with_a_doctor_department":[],
			"result_medical_record__name_of_doctor":[],
			"result_medical_record__department":[],
			"result_medical_apparatus_and_instruments_device_number":[],
			"result_medical_apparatus_and_instruments_purchase_date":[],
			"result_medical_apparatus_and_instruments_state":[],
			"result_online_consultation_name_of_doctor":[],
			"result_online_consultation_department":[],
			"result_maintenance_report_device_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取doctor_job_number
	 */
	get_doctor_doctor_job_number(){
		this.$get("~/api/doctor/get_list?like=0", { page: 1, size: 10, "doctor_job_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_doctor_job_number = json.result.list;
			result_doctor_doctor_job_number.map(o => o.title = o['doctor_job_number'])
	  			this.result_doctor_doctor_job_number = result_doctor_doctor_job_number
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_doctor_introduction_name_of_doctor(){
		this.$get("~/api/doctor_introduction/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_introduction_name_of_doctor = json.result.list;
			result_doctor_introduction_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_doctor_introduction_name_of_doctor = result_doctor_introduction_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取department
	 */
	get_doctor_introduction_department(){
		this.$get("~/api/doctor_introduction/get_list?like=0", { page: 1, size: 10, "department": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_introduction_department = json.result.list;
			result_doctor_introduction_department.map(o => o.title = o['department'])
	  			this.result_doctor_introduction_department = result_doctor_introduction_department
		 	}
		});
	},
	/**
	 * 获取order_number
	 */
	get_have_an_appointment_with_a_doctor_order_number(){
		this.$get("~/api/have_an_appointment_with_a_doctor/get_list?like=0", { page: 1, size: 10, "order_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_have_an_appointment_with_a_doctor_order_number = json.result.list;
			result_have_an_appointment_with_a_doctor_order_number.map(o => o.title = o['order_number'])
	  			this.result_have_an_appointment_with_a_doctor_order_number = result_have_an_appointment_with_a_doctor_order_number
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_have_an_appointment_with_a_doctor_name_of_doctor(){
		this.$get("~/api/have_an_appointment_with_a_doctor/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_have_an_appointment_with_a_doctor_name_of_doctor = json.result.list;
			result_have_an_appointment_with_a_doctor_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_have_an_appointment_with_a_doctor_name_of_doctor = result_have_an_appointment_with_a_doctor_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取department
	 */
	get_have_an_appointment_with_a_doctor_department(){
		this.$get("~/api/have_an_appointment_with_a_doctor/get_list?like=0", { page: 1, size: 10, "department": this.query.word }, (json) => {
		  if (json.result) {
			var result_have_an_appointment_with_a_doctor_department = json.result.list;
			result_have_an_appointment_with_a_doctor_department.map(o => o.title = o['department'])
	  			this.result_have_an_appointment_with_a_doctor_department = result_have_an_appointment_with_a_doctor_department
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_medical_record__name_of_doctor(){
		this.$get("~/api/medical_record_/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_medical_record__name_of_doctor = json.result.list;
			result_medical_record__name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_medical_record__name_of_doctor = result_medical_record__name_of_doctor
		 	}
		});
	},
	/**
	 * 获取department
	 */
	get_medical_record__department(){
		this.$get("~/api/medical_record_/get_list?like=0", { page: 1, size: 10, "department": this.query.word }, (json) => {
		  if (json.result) {
			var result_medical_record__department = json.result.list;
			result_medical_record__department.map(o => o.title = o['department'])
	  			this.result_medical_record__department = result_medical_record__department
		 	}
		});
	},
	/**
	 * 获取device_number
	 */
	get_medical_apparatus_and_instruments_device_number(){
		this.$get("~/api/medical_apparatus_and_instruments/get_list?like=0", { page: 1, size: 10, "device_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_medical_apparatus_and_instruments_device_number = json.result.list;
			result_medical_apparatus_and_instruments_device_number.map(o => o.title = o['device_number'])
	  			this.result_medical_apparatus_and_instruments_device_number = result_medical_apparatus_and_instruments_device_number
		 	}
		});
	},
	/**
	 * 获取purchase_date
	 */
	get_medical_apparatus_and_instruments_purchase_date(){
		this.$get("~/api/medical_apparatus_and_instruments/get_list?like=0", { page: 1, size: 10, "purchase_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_medical_apparatus_and_instruments_purchase_date = json.result.list;
			result_medical_apparatus_and_instruments_purchase_date.map(o => o.title = o['purchase_date'])
	  			this.result_medical_apparatus_and_instruments_purchase_date = result_medical_apparatus_and_instruments_purchase_date
		 	}
		});
	},
	/**
	 * 获取state
	 */
	get_medical_apparatus_and_instruments_state(){
		this.$get("~/api/medical_apparatus_and_instruments/get_list?like=0", { page: 1, size: 10, "state": this.query.word }, (json) => {
		  if (json.result) {
			var result_medical_apparatus_and_instruments_state = json.result.list;
			result_medical_apparatus_and_instruments_state.map(o => o.title = o['state'])
	  			this.result_medical_apparatus_and_instruments_state = result_medical_apparatus_and_instruments_state
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_online_consultation_name_of_doctor(){
		this.$get("~/api/online_consultation/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_consultation_name_of_doctor = json.result.list;
			result_online_consultation_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_online_consultation_name_of_doctor = result_online_consultation_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取department
	 */
	get_online_consultation_department(){
		this.$get("~/api/online_consultation/get_list?like=0", { page: 1, size: 10, "department": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_consultation_department = json.result.list;
			result_online_consultation_department.map(o => o.title = o['department'])
	  			this.result_online_consultation_department = result_online_consultation_department
		 	}
		});
	},
	/**
	 * 获取device_name
	 */
	get_maintenance_report_device_name(){
		this.$get("~/api/maintenance_report/get_list?like=0", { page: 1, size: 10, "device_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_maintenance_report_device_name = json.result.list;
			result_maintenance_report_device_name.map(o => o.title = o['device_name'])
	  			this.result_maintenance_report_device_name = result_maintenance_report_device_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_registered_user_user_name();
		this.get_doctor_doctor_job_number();
		this.get_doctor_introduction_name_of_doctor();
		this.get_doctor_introduction_department();
		this.get_have_an_appointment_with_a_doctor_order_number();
		this.get_have_an_appointment_with_a_doctor_name_of_doctor();
		this.get_have_an_appointment_with_a_doctor_department();
		this.get_medical_record__name_of_doctor();
		this.get_medical_record__department();
		this.get_medical_apparatus_and_instruments_device_number();
		this.get_medical_apparatus_and_instruments_purchase_date();
		this.get_medical_apparatus_and_instruments_state();
		this.get_online_consultation_name_of_doctor();
		this.get_online_consultation_department();
		this.get_maintenance_report_device_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_registered_user_user_name();
	  this.get_doctor_doctor_job_number();
	  this.get_doctor_introduction_name_of_doctor();
	  this.get_doctor_introduction_department();
	  this.get_have_an_appointment_with_a_doctor_order_number();
	  this.get_have_an_appointment_with_a_doctor_name_of_doctor();
	  this.get_have_an_appointment_with_a_doctor_department();
	  this.get_medical_record__name_of_doctor();
	  this.get_medical_record__department();
	  this.get_medical_apparatus_and_instruments_device_number();
	  this.get_medical_apparatus_and_instruments_purchase_date();
	  this.get_medical_apparatus_and_instruments_state();
	  this.get_online_consultation_name_of_doctor();
	  this.get_online_consultation_department();
	  this.get_maintenance_report_device_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
