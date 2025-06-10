<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="订单号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单号"
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','order_number')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','order_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_job_number') || $check_field('add','doctor_job_number') || $check_field('set','doctor_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生工号" prop="doctor_job_number">
						<el-select v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','doctor_job_number')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','doctor_job_number'))" id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="disabledObj['doctor_job_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','name_of_doctor')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','name_of_doctor'))" :disabled="disabledObj['name_of_doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_doctor')">{{form['name_of_doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department') || $check_field('add','department') || $check_field('set','department')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="科室" prop="department">
					<el-input id="department" v-model="form['department']" placeholder="请输入科室"
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','department')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','department'))" :disabled="disabledObj['department_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department')">{{form['department']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','registration_fee') || $check_field('add','registration_fee') || $check_field('set','registration_fee')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="挂号费" prop="registration_fee">
					<el-input id="registration_fee" v-model="form['registration_fee']" placeholder="请输入挂号费"
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','registration_fee')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','registration_fee'))" :disabled="disabledObj['registration_fee_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','registration_fee')">{{form['registration_fee']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户名" prop="user_name">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_name(form['user_name']) }}
							<!--<el-input id="business_name" v-model="form['user_name']" placeholder="请输入用户名"-->
							<!--v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','user_name')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','user_name')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','user_name'))" id="user_name" v-model="form['user_name']" :disabled="disabledObj['user_name_isDisabled']">
								<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user_name')" id="user_name" v-model="form['user_name']" :disabled="true">
								<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user_name" v-model="form['user_name']" :disabled="disabledObj['user_name_isDisabled']">
							<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pet_variety') || $check_field('add','pet_variety') || $check_field('set','pet_variety')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="宠物品种" prop="pet_variety">
					<el-input id="pet_variety" v-model="form['pet_variety']" placeholder="请输入宠物品种"
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','pet_variety')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','pet_variety'))" :disabled="disabledObj['pet_variety_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','pet_variety')">{{form['pet_variety']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pet_age') || $check_field('add','pet_age') || $check_field('set','pet_age')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="宠物年龄" prop="pet_age">
					<el-input id="pet_age" v-model="form['pet_age']" placeholder="请输入宠物年龄"
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','pet_age')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','pet_age'))" :disabled="disabledObj['pet_age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','pet_age')">{{form['pet_age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pet_sex') || $check_field('add','pet_sex') || $check_field('set','pet_sex')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="宠物性别" prop="pet_sex">
					<el-select id="pet_sex" v-model="form['pet_sex']"
						v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','pet_sex')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','pet_sex'))">
						<el-option v-for="o in list_pet_sex" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','pet_sex')">{{form['pet_sex']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['have_an_appointment_with_a_doctor_id'] && $check_field('set','contact_number')) || (!form['have_an_appointment_with_a_doctor_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
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
				field: "have_an_appointment_with_a_doctor_id",
				url_add: "~/api/have_an_appointment_with_a_doctor/add?",
				url_set: "~/api/have_an_appointment_with_a_doctor/set?",
				url_get_obj: "~/api/have_an_appointment_with_a_doctor/get_obj?",
				url_upload: "~/api/have_an_appointment_with_a_doctor/upload?",

				query: {
					"have_an_appointment_with_a_doctor_id": 0,
				},

				form: {
					"order_number":this.$get_stamp(), // 订单号
					"doctor_job_number": 0, // 医生工号
					"name_of_doctor":'', // 医生姓名
					"department":'', // 科室
					"registration_fee":'', // 挂号费
					"user_name": 0, // 用户名
					"pet_variety":'', // 宠物品种
					"pet_age":'', // 宠物年龄
					"pet_sex":'', // 宠物性别
					"contact_number":'', // 联系电话
					"have_an_appointment_with_a_doctor_id": 0, // ID

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"doctor_job_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"department_isDisabled": false,
					"registration_fee_isDisabled": false,
					"user_name_isDisabled": false,
					"pet_variety_isDisabled": false,
					"pet_age_isDisabled": false,
					"pet_sex_isDisabled": false,
					"contact_number_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_job_number: [],
				// 用户列表
				list_user_user_name: [],
				// 用户组
				group_user_user_name: "",
				//宠物性别选项列表
				list_pet_sex: ['公','母'],

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
			/**
			 * 获取注册用户用户组
			 */
			async get_group_user_user_name() {
				this.form["user_name"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					this.group_user_user_name = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user_name(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user_name.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="user_name") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
					bl = this.$check_action('/have_an_appointment_with_a_doctor/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/have_an_appointment_with_a_doctor/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/have_an_appointment_with_a_doctor/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/have_an_appointment_with_a_doctor/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/have_an_appointment_with_a_doctor/view','get');
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
			this.get_group_user_user_name();
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
