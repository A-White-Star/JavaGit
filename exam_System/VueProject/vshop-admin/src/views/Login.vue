<template>
	<div class="login_background" style="width: 100%; height: 100%;">
		<div style="height: 200px"></div>
		    <div class="login"  >
				  <el-form ref="form" :model="form" :rules="rules" >
				<h3 class="title">系统登录</h3>
				<el-form-item label="用户名" prop="username">
					<el-input type="text" v-model="form.username" autocomplete="off" placeholder="请输入用户名"
						@keydown.enter.native="mimaFocus"></el-input>
				</el-form-item>
				<el-form-item label="密&nbsp;&nbsp;码" prop="password">
					<el-input ref="txtPwd" type="password" v-model="form.password" autocomplete="off" placeholder="请输入密码"
						@keydown.enter.native="onSubmit"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button class="btnSubmit" round @click="onSubmit">ENTER</el-button>
				</el-form-item>
			</el-form> 
		    </div> 
	</div>
</template>

<!-- js部分 -->
<script>
	export default {
		data() {
			return {
				form: {
					username: '',
					password: ''
				},
				rules: {
					username: {
						required: true,
						message: '请输入用户名',
						triggere: 'blur'
					},
					password: {
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					},

				}
				//          imgSrc:require('~@/assets/psc.png')
			}
		},
		methods: {
			mimaFocus() {
				this.$refs.txtPwd.focus()
			},
			onSubmit() {
				let that = this
				this.$refs.form.validate((valid) => {
					if (valid) {
						let data = {
							"username": that.form.username,
							"password": that.form.password,
						}
						that.$api.postKeyValueRequest("/api/login", data).then(res => {
							if (res) {
								window.sessionStorage.setItem("user", JSON.stringify(res.responseData))
								that.$router.replace("/index") //跳转
							}
						})
					} else {
						this.$message.error("请输入用户名和密码")
						return false
					}
				})
			}
		}
	}
</script>

<style>
	.login_background{
		/* background-image: url("../assets/psc.png"); */
		background-image: url("../assets/20210712214408_190fc.png");
		background-size:1600px 1000px
		}
	.login {
		width: 300px;
		margin: auto;
		background-color: #dcecf5;
		position: relative;
		border: 2px solid #2c91c5;
		border-radius: 15px;
		padding: 15px 50px;
		box-shadow: 0 0 25px #2c90c5;
	}

	.login .title {
		text-align: center;
	}

	.btnSubmit {
		width: 100%;
	}
</style>
