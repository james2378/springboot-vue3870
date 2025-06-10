<template>
	<div class="diy_edit page_online_consultation" id="online_consultation_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','doctor_job_number') || $check_field('add','doctor_job_number') || $check_field('get','doctor_job_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>医生工号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_doctor_job_number" :disabled="disabledObj['doctor_job_number_isDisabled']" v-model="form['doctor_job_number']" v-if="(form['doctor_job_number'] && $check_field('set','doctor_job_number')) || (!form['doctor_job_number'] && $check_field('add','doctor_job_number'))" >
								<option v-for="o in list_user_doctor_job_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','doctor_job_number')">{{ form['doctor_job_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','name_of_doctor') || $check_field('add','name_of_doctor') || $check_field('get','name_of_doctor')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>医生姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_name_of_doctor" v-model="form['name_of_doctor']" placeholder="请输入医生姓名" v-if="(form['name_of_doctor'] && $check_field('set','name_of_doctor')) || (!form['name_of_doctor'] && $check_field('add','name_of_doctor'))"  :disabled="disabledObj['name_of_doctor_isDisabled']"/>
							<span v-else-if="$check_field('get','name_of_doctor')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','department') || $check_field('add','department') || $check_field('get','department')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>科室:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_department" v-model="form['department']" placeholder="请输入科室" v-if="(form['department'] && $check_field('set','department')) || (!form['department'] && $check_field('add','department'))"  :disabled="disabledObj['department_isDisabled']"/>
							<span v-else-if="$check_field('get','department')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>用户名:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user_name" :disabled="disabledObj['user_name_isDisabled']" v-model="form['user_name']" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))" >
								<option v-for="o in list_user_user_name" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user_name')">{{ form['user_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','pet_variety') || $check_field('add','pet_variety') || $check_field('get','pet_variety')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>宠物品种:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_pet_variety" v-model="form['pet_variety']" placeholder="请输入宠物品种" v-if="(form['pet_variety'] && $check_field('set','pet_variety')) || (!form['pet_variety'] && $check_field('add','pet_variety'))"  :disabled="disabledObj['pet_variety_isDisabled']"/>
							<span v-else-if="$check_field('get','pet_variety')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','consulting_questions') || $check_field('add','consulting_questions') || $check_field('get','consulting_questions')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>咨询问题:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_consulting_questions" v-model="form['consulting_questions']" v-if="(form['consulting_questions'] && $check_field('set','consulting_questions')) || (!form['consulting_questions'] && $check_field('add','consulting_questions'))" :disabled="disabledObj['consulting_questions_isDisabled']" />
							<span v-else-if="$check_field('get','consulting_questions')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','doctor_reply') || $check_field('add','doctor_reply') || $check_field('get','doctor_reply')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>医生回复:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_doctor_reply" v-model="form['doctor_reply']" v-if="(form['doctor_reply'] && $check_field('set','doctor_reply')) || (!form['doctor_reply'] && $check_field('add','doctor_reply'))" :disabled="disabledObj['doctor_reply_isDisabled']" />
							<span v-else-if="$check_field('get','doctor_reply')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/online_consultation/get_obj?",
				url_add: "~/api/online_consultation/add?",
				url_set: "~/api/online_consultation/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"doctor_job_number": 0,
					"name_of_doctor": "",
					"department": "",
					"user_name": 0,
					"pet_variety": "",
					"consulting_questions": "",
					"doctor_reply": "",
					"online_consultation_id": 0,
				},

				obj: {
					"doctor_job_number": 0, // 医生工号
					"name_of_doctor":'', // 医生姓名
					"department":'', // 科室
					"user_name": 0, // 用户名
					"pet_variety":'', // 宠物品种
					"consulting_questions":'', // 咨询问题
					"doctor_reply":'', // 医生回复
					"online_consultation_id": 0,
				},

				// 表单字段
				form: {
					"doctor_job_number": 0, // 医生工号
					"name_of_doctor":'', // 医生姓名
					"department":'', // 科室
					"user_name": 0, // 用户名
					"pet_variety":'', // 宠物品种
					"consulting_questions":'', // 咨询问题
					"doctor_reply":'', // 医生回复
					"online_consultation_id": 0,

				},
				disabledObj:{
					"doctor_job_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"department_isDisabled": false,
					"user_name_isDisabled": false,
					"pet_variety_isDisabled": false,
					"consulting_questions_isDisabled": false,
					"doctor_reply_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_job_number: [],
				// 用户列表
				list_user_user_name: [],

				// ID字段
				field: "online_consultation_id",
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
			async get_user_session_user_name(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user_name"] = user_id
								_this.disabledObj['user_name' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user_name") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/online_consultation/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_list_user_doctor_job_number();
			this.get_user_session_user_name();
			this.get_list_user_user_name();
		}
	}
</script>

<style>
</style>
