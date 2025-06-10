<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','device_number') || $check_field('add','device_number') || $check_field('set','device_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="器械编号" prop="device_number">
					<el-input id="device_number" v-model="form['device_number']" placeholder="请输入器械编号"
							  v-if="user_group === '管理员' || (form['maintenance_report_id'] && $check_field('set','device_number')) || (!form['maintenance_report_id'] && $check_field('add','device_number'))" :disabled="disabledObj['device_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','device_number')">{{form['device_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','device_name') || $check_field('add','device_name') || $check_field('set','device_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="器械名称" prop="device_name">
					<el-input id="device_name" v-model="form['device_name']" placeholder="请输入器械名称"
							  v-if="user_group === '管理员' || (form['maintenance_report_id'] && $check_field('set','device_name')) || (!form['maintenance_report_id'] && $check_field('add','device_name'))" :disabled="disabledObj['device_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','device_name')">{{form['device_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','related_pictures') || $check_field('add','related_pictures') || $check_field('set','related_pictures')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="相关图片" prop="related_pictures">
					<el-upload :disabled="disabledObj['related_pictures_isDisabled']" id="related_pictures" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_related_pictures"
						:show-file-list="false" v-if="user_group === '管理员' || (form['maintenance_report_id'] && $check_field('set','related_pictures')) || (!form['maintenance_report_id'] && $check_field('add','related_pictures'))">
						<img v-if="form['related_pictures']" :src="$fullUrl(form['related_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','related_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['related_pictures'])" :preview-src-list="[$fullUrl(form['related_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_job_number') || $check_field('add','doctor_job_number') || $check_field('set','doctor_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生工号" prop="doctor_job_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_doctor_job_number(form['doctor_job_number']) }}
							<!--<el-input id="business_name" v-model="form['doctor_job_number']" placeholder="请输入医生工号"-->
							<!--v-if="user_group === '管理员' || (form['maintenance_report_id'] && $check_field('set','doctor_job_number')) || (!form['maintenance_report_id'] && $check_field('add','doctor_job_number'))" :disabled="disabledObj['doctor_job_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','doctor_job_number')">{{form['doctor_job_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['maintenance_report_id'] && $check_field('set','doctor_job_number')) || (!form['maintenance_report_id'] && $check_field('add','doctor_job_number'))" id="doctor_job_number" v-model="form['doctor_job_number']" :disabled="disabledObj['doctor_job_number_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','fault_description') || $check_field('add','fault_description') || $check_field('set','fault_description')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="故障描述" prop="fault_description">
					<el-input type="textarea" id="fault_description" v-model="form['fault_description']" placeholder="请输入故障描述"
						v-if="user_group === '管理员' || (form['maintenance_report_id'] && $check_field('set','fault_description')) || (!form['maintenance_report_id'] && $check_field('add','fault_description'))" :disabled="disabledObj['fault_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','fault_description')">{{form['fault_description']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else-if="$check_field('get','examine_reply')">{{form["examine_reply"]}}</div>
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
				field: "maintenance_report_id",
				url_add: "~/api/maintenance_report/add?",
				url_set: "~/api/maintenance_report/set?",
				url_get_obj: "~/api/maintenance_report/get_obj?",
				url_upload: "~/api/maintenance_report/upload?",

				query: {
					"maintenance_report_id": 0,
				},

				form: {
					"device_number":'', // 器械编号
					"device_name":'', // 器械名称
					"related_pictures":'', // 相关图片
					"doctor_job_number": 0, // 医生工号
					"fault_description":'', // 故障描述
					"examine_state": "未审核",
					"examine_reply": "",
					"maintenance_report_id": 0, // ID

				},
				disabledObj:{
					"device_number_isDisabled": false,
					"device_name_isDisabled": false,
					"related_pictures_isDisabled": false,
					"doctor_job_number_isDisabled": false,
					"fault_description_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_job_number: [],
				// 用户组
				group_user_doctor_job_number: "",

			}
		},
		methods: {
			/**
			 * 上传相关图片
			 * @param {Object} param图片参数
			 */
			upload_related_pictures(param){
				this.uploadFile(param.file, "related_pictures");
			},
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
					bl = this.$check_action('/maintenance_report/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/maintenance_report/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/maintenance_report/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/maintenance_report/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/maintenance_report/view','get');
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
