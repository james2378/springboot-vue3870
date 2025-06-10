<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="订单号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单号"
							  v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','order_number')) || (!form['medical_record__id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_job_number') || $check_field('add','doctor_job_number') || $check_field('set','doctor_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生工号" prop="doctor_job_number">
						<el-select v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','doctor_job_number')) || (!form['medical_record__id'] && $check_field('add','doctor_job_number'))" id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="disabledObj['doctor_job_number_isDisabled']">
							<el-option v-for="o in list_user_doctor_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','doctor_job_number')" id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="true">
							<el-option v-for="o in list_user_doctor_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_doctor') || $check_field('add','name_of_doctor') || $check_field('set','name_of_doctor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生姓名" prop="name_of_doctor">
					<el-input id="name_of_doctor" v-model="form['name_of_doctor']" placeholder="请输入医生姓名"
							  v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','name_of_doctor')) || (!form['medical_record__id'] && $check_field('add','name_of_doctor'))" :disabled="disabledObj['name_of_doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_doctor')">{{form['name_of_doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department') || $check_field('add','department') || $check_field('set','department')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="科室" prop="department">
					<el-input id="department" v-model="form['department']" placeholder="请输入科室"
							  v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','department')) || (!form['medical_record__id'] && $check_field('add','department'))" :disabled="disabledObj['department_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department')">{{form['department']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户名" prop="user_name">
						<el-select v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','user_name')) || (!form['medical_record__id'] && $check_field('add','user_name'))" id="user_name" v-model="form['user_name']" :disabled="disabledObj['user_name_isDisabled']">
							<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','user_name')" id="user_name" v-model="form['user_name']" :disabled="true">
							<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pet_variety') || $check_field('add','pet_variety') || $check_field('set','pet_variety')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="宠物品种" prop="pet_variety">
					<el-input id="pet_variety" v-model="form['pet_variety']" placeholder="请输入宠物品种"
							  v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','pet_variety')) || (!form['medical_record__id'] && $check_field('add','pet_variety'))" :disabled="disabledObj['pet_variety_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','pet_variety')">{{form['pet_variety']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pet_age') || $check_field('add','pet_age') || $check_field('set','pet_age')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="宠物年龄" prop="pet_age">
					<el-input id="pet_age" v-model="form['pet_age']" placeholder="请输入宠物年龄"
							  v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','pet_age')) || (!form['medical_record__id'] && $check_field('add','pet_age'))" :disabled="disabledObj['pet_age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','pet_age')">{{form['pet_age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','symptom_description') || $check_field('add','symptom_description') || $check_field('set','symptom_description')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="症状描述" prop="symptom_description">
					<el-input type="textarea" id="symptom_description" v-model="form['symptom_description']" placeholder="请输入症状描述"
						v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','symptom_description')) || (!form['medical_record__id'] && $check_field('add','symptom_description'))" :disabled="disabledObj['symptom_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','symptom_description')">{{form['symptom_description']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clinical_diagnosis') || $check_field('add','clinical_diagnosis') || $check_field('set','clinical_diagnosis')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="临床诊断" prop="clinical_diagnosis">
					<el-input type="textarea" id="clinical_diagnosis" v-model="form['clinical_diagnosis']" placeholder="请输入临床诊断"
						v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','clinical_diagnosis')) || (!form['medical_record__id'] && $check_field('add','clinical_diagnosis'))" :disabled="disabledObj['clinical_diagnosis_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','clinical_diagnosis')">{{form['clinical_diagnosis']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','drug_prescription') || $check_field('add','drug_prescription') || $check_field('set','drug_prescription')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="药物处方" prop="drug_prescription">
					<el-input type="textarea" id="drug_prescription" v-model="form['drug_prescription']" placeholder="请输入药物处方"
						v-if="user_group === '管理员' || (form['medical_record__id'] && $check_field('set','drug_prescription')) || (!form['medical_record__id'] && $check_field('add','drug_prescription'))" :disabled="disabledObj['drug_prescription_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','drug_prescription')">{{form['drug_prescription']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "medical_record__id",
				url_add: "~/api/medical_record_/add?",
				url_set: "~/api/medical_record_/set?",
				url_get_obj: "~/api/medical_record_/get_obj?",
				url_upload: "~/api/medical_record_/upload?",

				query: {
					"medical_record__id": 0,
				},

				form: {
					"order_number":'', // 订单号
					"doctor_job_number": 0, // 医生工号
					"name_of_doctor":'', // 医生姓名
					"department":'', // 科室
					"user_name": 0, // 用户名
					"pet_variety":'', // 宠物品种
					"pet_age":'', // 宠物年龄
					"symptom_description":'', // 症状描述
					"clinical_diagnosis":'', // 临床诊断
					"drug_prescription":'', // 药物处方
					"medical_record__id": 0, // ID

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"doctor_job_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"department_isDisabled": false,
					"user_name_isDisabled": false,
					"pet_variety_isDisabled": false,
					"pet_age_isDisabled": false,
					"symptom_description_isDisabled": false,
					"clinical_diagnosis_isDisabled": false,
					"drug_prescription_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_job_number: [],
				// 用户列表
				list_user_user_name: [],

			}
		},
		methods: {
			/**
			 * 获取医生用户列表
			 */
			async get_list_user_doctor_job_number() {
                // if(this.user_group !== "管理员" && this.form["doctor_job_number"] === 0) {
                //     this.form["doctor_job_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=医生");
                if(json.result && json.result.list){
                    this.list_user_doctor_job_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_doctor_job_number(id){
				var obj = this.list_user_doctor_job_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_user_name() {
                // if(this.user_group !== "管理员" && this.form["user_name"] === 0) {
                //     this.form["user_name"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=注册用户");
                if(json.result && json.result.list){
                    this.list_user_user_name = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_user_name(id){
				var obj = this.list_user_user_name.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "医生工号":
							if(param["doctor_job_number"] > 0){
								param["doctor_job_number"] = this.user.user_id;
							}
							break;
						case "用户名":
							if(param["user_name"] > 0){
								param["user_name"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/medical_record_/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_record_/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_record_/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_record_/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_record_/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_doctor_job_number();
			this.get_list_user_user_name();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
