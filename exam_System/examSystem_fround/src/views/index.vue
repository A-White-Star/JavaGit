<template>
	<el-container>
		<el-header class="header">
			<div class="title">
				微小店の后台管理系统
			</div>
			<el-dropdown @command="handleCommand">
				<span class="el-dropdown-link">
					<i><span class="img">{{user.zhenShiXingMing.substring(0,1)}}</span></i>
				</span>
				<template #dropdown>
					<el-dropdown-menu>
						<el-dropdown-item>个人中心</el-dropdown-item>
						<el-dropdown-item>设置</el-dropdown-item>
						<el-dropdown-item command="logout" divided icon="fa fa-sign-out">注销登录</el-dropdown-item>
					</el-dropdown-menu>
				</template>

			</el-dropdown>
		</el-header>
		<el-container>
			<el-aside width="200px">
				<el-menu class="el-menu-vertical-demo" router unique-opened active-text-color="#edbbbc"
					background-color="#4c7173" text-color="#fff">
					<template v-for="(item,index) in routes" :key="index">
						<el-submenu :index="index+''" v-if="!item.hidden">
							<template #title>
								<i :class=" item.icon" style="margin-right: 5px;color: #7ea0b2" />
								<span>{{item.name}}</span>
							</template>
							<el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">
								<i :class="child.icon" style="margin-right: 5px;color: #a8bdbe" />{{child.name}}
								<!-- 元素内用margin，元素外用addin -->
							</el-menu-item>
						</el-submenu>
					</template>
				</el-menu>
			</el-aside>
			<el-main>
				<el-breadcrumb  separator=">>" v-if="this.$route.name !== 'index'">
					<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>{{this.$route.name}}</el-breadcrumb-item>
				</el-breadcrumb>
				<div class="welcome" v-if="this.$route.name == 'index'" >
					欢迎使用V-shop
				</div>
				<router-view style="margin-top: 10px;"/>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import {ElMessageBox} from 'element-plus'
	export default {
		data() {
			return {
//arrowRight:ArrowRight,
				user: JSON.parse(window.sessionStorage.getItem("user"))
			}
		},
		computed: {
			routes() {
				return this.$store.state.routes
			}
		},
		methods: {
			// menuClick(index,indexPath){
			// 	this.$router.push(index)
			// },
			handleCommand(cmd) {
				let that = this
				if (cmd == "logout") {
					ElMessageBox.confirm(
							'您真的要退出系统吗?',
							'提示', {
								confirmButtonText: '确定',
								cancelButtonText: '取消',
								type: 'warning',
							}
						)
						.then(() => {
							that.$api.getRequest("/api/logout")
							window.sessionStorage.removeItem("user")
							//this.$store.commit('routes',[])
							this.$store.state.routes = []
							this.$router.replace("/")
						})
						.catch(() => {
							that.$message({
								type: 'info',
								message: '已取消操作',
							})
						})
				}

			}
		}

	}
</script>

<style>
	.welcome{
		background-color: #e5e0e1;
		font-size: 30px;
		color:#a0aecb;
		text-align: center;
		margin-top:150px;
	}
	.header {
		background-color:#385a59;
		display: flex;
		align-items: center;
		padding: 25px 100px !important;
		justify-content: space-between;

	}

	.header .title {
		font-size: 30px;
		color: #fcfcfb;
		font-family: 华文黑体;
	}

	.img {
		border: 1px solid #26494b;
		background-color: #7ea1af;
		width: 48px;
		height: 48px;
		display: inline-flex;
		align-items: center;
		margin-left: 10px;
		border-radius: 24px;
		box-sizing: border-box;
		padding: 15px;
	}
</style>
