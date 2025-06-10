<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','device_number') || $check_field('add','device_number') || $check_field('set','device_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="器械编号" prop="device_number">
					<el-input id="device_number" v-model="form['device_number']" placeholder="请输入器械编号"
							  v-if="user_group === '管理员' || (form['medical_apparatus_and_instruments_id'] && $check_field('set','device_number')) || (!form['medical_apparatus_and_instruments_id'] && $check_field('add','device_number'))" :disabled="disabledObj['device_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','device_number')">{{form['device_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','device_name') || $check_field('add','device_name') || $check_field('set','device_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="器械名称" prop="device_name">
					<el-input id="device_name" v-model="form['device_name']" placeholder="请输入器械名称"
							  v-if="user_group === '管理员' || (form['medical_apparatus_and_instruments_id'] && $check_field('set','device_name')) || (!form['medical_apparatus_and_instruments_id'] && $check_field('add','device_name'))" :disabled="disabledObj['device_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','device_name')">{{form['device_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchase_date') || $check_field('add','purchase_date') || $check_field('set','purchase_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购入日期" prop="purchase_date">
					<el-date-picker :disabled="disabledObj['purchase_date_isDisabled']" v-if="user_group === '管理员' || (form['medical_apparatus_and_instruments_id'] && $check_field('set','purchase_date')) || (!form['medical_apparatus_and_instruments_id'] && $check_field('add','purchase_date'))" id="purchase_date"
						v-model="form['purchase_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','purchase_date')">{{form['purchase_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','manufactor') || $check_field('add','manufactor') || $check_field('set','manufactor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="厂家" prop="manufactor">
					<el-input id="manufactor" v-model="form['manufactor']" placeholder="请输入厂家"
							  v-if="user_group === '管理员' || (form['medical_apparatus_and_instruments_id'] && $check_field('set','manufactor')) || (!form['medical_apparatus_and_instruments_id'] && $check_field('add','manufactor'))" :disabled="disabledObj['manufactor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','manufactor')">{{form['manufactor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','state') || $check_field('add','state') || $check_field('set','state')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="状态" prop="state">
					<el-select id="state" v-model="form['state']"
						v-if="user_group === '管理员' || (form['medical_apparatus_and_instruments_id'] && $check_field('set','state')) || (!form['medical_apparatus_and_instruments_id'] && $check_field('add','state'))">
						<el-option v-for="o in list_state" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','state')">{{form['state']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','maintenance_record') || $check_field('add','maintenance_record') || $check_field('set','maintenance_record')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="维修记录" prop="maintenance_record">
					<el-input type="textarea" id="maintenance_record" v-model="form['maintenance_record']" placeholder="请输入维修记录"
						v-if="user_group === '管理员' || (form['medical_apparatus_and_instruments_id'] && $check_field('set','maintenance_record')) || (!form['medical_apparatus_and_instruments_id'] && $check_field('add','maintenance_record'))" :disabled="disabledObj['maintenance_record_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','maintenance_record')">{{form['maintenance_record']}}</div>
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
				field: "medical_apparatus_and_instruments_id",
				url_add: "~/api/medical_apparatus_and_instruments/add?",
				url_set: "~/api/medical_apparatus_and_instruments/set?",
				url_get_obj: "~/api/medical_apparatus_and_instruments/get_obj?",
				url_upload: "~/api/medical_apparatus_and_instruments/upload?",

				query: {
					"medical_apparatus_and_instruments_id": 0,
				},

				form: {
					"device_number":'', // 器械编号
					"device_name":'', // 器械名称
					"purchase_date":'', // 购入日期
					"manufactor":'', // 厂家
					"state":'', // 状态
					"maintenance_record":'', // 维修记录
					"medical_apparatus_and_instruments_id": 0, // ID

				},
				disabledObj:{
					"device_number_isDisabled": false,
					"device_name_isDisabled": false,
					"purchase_date_isDisabled": false,
					"manufactor_isDisabled": false,
					"state_isDisabled": false,
					"maintenance_record_isDisabled": false,
				},
				//状态选项列表
				list_state: ['正常','故障','维修中'],

			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
				this.form["purchase_date"] = this.$toTime(parseInt(this.form["purchase_date"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["purchase_date"]) > 9999){
					this.form["purchase_date"] = this.$toTime(parseInt(this.form["purchase_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/medical_apparatus_and_instruments/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_apparatus_and_instruments/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_apparatus_and_instruments/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_apparatus_and_instruments/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/medical_apparatus_and_instruments/view','get');
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
