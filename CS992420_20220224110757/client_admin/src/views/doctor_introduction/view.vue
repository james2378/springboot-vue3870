<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_job_number') || $check_field('add','doctor_job_number') || $check_field('set','doctor_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生工号" prop="doctor_job_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_doctor_job_number(form['doctor_job_number']) }}
							<!--<el-input id="business_name" v-model="form['doctor_job_number']" placeholder="请输入医生工号"-->
							<!--v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','doctor_job_number')) || (!form['doctor_introduction_id'] && $check_field('add','doctor_job_number'))" :disabled="disabledObj['doctor_job_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','doctor_job_number')">{{form['doctor_job_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','doctor_job_number')) || (!form['doctor_introduction_id'] && $check_field('add','doctor_job_number'))" id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="disabledObj['doctor_job_number_isDisabled']">
								<el-option v-for="o in list_user_doctor_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','doctor_job_number')" id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="true">
								<el-option v-for="o in list_user_doctor_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="disabledObj['doctor_job_number_isDisabled']">
							<el-option v-for="o in list_user_doctor_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_doctor') || $check_field('add','name_of_doctor') || $check_field('set','name_of_doctor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生姓名" prop="name_of_doctor">
					<el-input id="name_of_doctor" v-model="form['name_of_doctor']" placeholder="请输入医生姓名"
							  v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','name_of_doctor')) || (!form['doctor_introduction_id'] && $check_field('add','name_of_doctor'))" :disabled="disabledObj['name_of_doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_doctor')">{{form['name_of_doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="性别" prop="gender">
					<el-select id="gender" v-model="form['gender']"
						v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','gender')) || (!form['doctor_introduction_id'] && $check_field('add','gender'))">
						<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','age') || $check_field('add','age') || $check_field('set','age')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="年龄" prop="age">
					<el-input id="age" v-model="form['age']" placeholder="请输入年龄"
							  v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','age')) || (!form['doctor_introduction_id'] && $check_field('add','age'))" :disabled="disabledObj['age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','age')">{{form['age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','photo') || $check_field('add','photo') || $check_field('set','photo')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="照片" prop="photo">
					<el-upload :disabled="disabledObj['photo_isDisabled']" id="photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','photo')) || (!form['doctor_introduction_id'] && $check_field('add','photo'))">
						<img v-if="form['photo']" :src="$fullUrl(form['photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['photo'])" :preview-src-list="[$fullUrl(form['photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department') || $check_field('add','department') || $check_field('set','department')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="科室" prop="department">
					<el-input id="department" v-model="form['department']" placeholder="请输入科室"
							  v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','department')) || (!form['doctor_introduction_id'] && $check_field('add','department'))" :disabled="disabledObj['department_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department')">{{form['department']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','registration_fee') || $check_field('add','registration_fee') || $check_field('set','registration_fee')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="挂号费" prop="registration_fee">
					<el-input-number id="registration_fee" v-model.number="form['registration_fee']"
						v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','registration_fee')) || (!form['doctor_introduction_id'] && $check_field('add','registration_fee'))"></el-input-number>
					<div v-else-if="$check_field('get','registration_fee')">{{form['registration_fee']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','areas_of_expertise') || $check_field('add','areas_of_expertise') || $check_field('set','areas_of_expertise')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="擅长领域" prop="areas_of_expertise">
					<el-input type="textarea" id="areas_of_expertise" v-model="form['areas_of_expertise']" placeholder="请输入擅长领域"
						v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','areas_of_expertise')) || (!form['doctor_introduction_id'] && $check_field('add','areas_of_expertise'))" :disabled="disabledObj['areas_of_expertise_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','areas_of_expertise')">{{form['areas_of_expertise']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','scheduling_time') || $check_field('add','scheduling_time') || $check_field('set','scheduling_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="排班时间" prop="scheduling_time">
					<el-input type="textarea" id="scheduling_time" v-model="form['scheduling_time']" placeholder="请输入排班时间"
						v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','scheduling_time')) || (!form['doctor_introduction_id'] && $check_field('add','scheduling_time'))" :disabled="disabledObj['scheduling_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','scheduling_time')">{{form['scheduling_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_introduction') || $check_field('add','doctor_introduction') || $check_field('set','doctor_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="医生介绍" prop="doctor_introduction">
					<quill-editor v-model.number="form['doctor_introduction']"
						v-if="user_group === '管理员' || (form['doctor_introduction_id'] && $check_field('set','doctor_introduction')) || (!form['doctor_introduction_id'] && $check_field('add','doctor_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','doctor_introduction')" v-html="form['doctor_introduction']"></div>
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
				field: "doctor_introduction_id",
				url_add: "~/api/doctor_introduction/add?",
				url_set: "~/api/doctor_introduction/set?",
				url_get_obj: "~/api/doctor_introduction/get_obj?",
				url_upload: "~/api/doctor_introduction/upload?",

				query: {
					"doctor_introduction_id": 0,
				},

				form: {
					"doctor_job_number": 0, // 医生工号
					"name_of_doctor":'', // 医生姓名
					"gender":'', // 性别
					"age":'', // 年龄
					"photo":'', // 照片
					"department":'', // 科室
					"registration_fee":0, // 挂号费
					"areas_of_expertise":'', // 擅长领域
					"scheduling_time":'', // 排班时间
					"doctor_introduction":'', // 医生介绍
					"doctor_introduction_id": 0, // ID

				},
				disabledObj:{
					"doctor_job_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"gender_isDisabled": false,
					"age_isDisabled": false,
					"photo_isDisabled": false,
					"department_isDisabled": false,
					"areas_of_expertise_isDisabled": false,
					"scheduling_time_isDisabled": false,
					"doctor_introduction_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_job_number: [],
				// 用户组
				group_user_doctor_job_number: "",
				//性别选项列表
				list_gender: ['男','女'],

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
			/**
			 * 获取医生用户组
			 */
			async get_group_user_doctor_job_number() {
				this.form["doctor_job_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=医生");
				if(json.result && json.result.obj){
					this.group_user_doctor_job_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_doctor_job_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_doctor_job_number.source_table+"/get_obj?"
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
									if (arr[i]!=="doctor_job_number") {
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
			 * 上传照片
			 * @param {Object} param图片参数
			 */
			upload_photo(param){
				this.uploadFile(param.file, "photo");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/doctor_introduction/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_introduction/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_introduction/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_introduction/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_introduction/view','get');
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
			this.get_group_user_doctor_job_number();
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
