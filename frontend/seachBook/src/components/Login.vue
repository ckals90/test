<template>
  <div>
	<form @submit.prevent="onSubmit" target="/comLogin" style="text-align:center">
		<h2>Log In</h2>
		<input placeholder="ID를 입력해주세요."  name="user_id" v-model="user_id">
		<input placeholder="Password를 입력해주세요." type="password" name="user_pw" v-model="user_pw">
		<button type="submit">Login</button>
		<router-link :to="{name:'join'}">
			<button type="button">Join</button>
		</router-link>
	</form>
  </div>
</template>

<script>
export default {
  name: 'app',
  data: () => ({
    user_id: '',
    user_pw: ''
  }),
  methods: {
    onSubmit () {
		var data = new FormData();

		data.append('user_id',this.user_id);
		data.append('user_pw',this.user_pw);
		this.$http.post('/com/doLogin',data)
		.then((response)=>{
			if(response.data=="FAIL"){
				alert("계정이 존재하지 않거나 패스워드가 틀립니다.");
			}else{
				this.$router.push({
					name :'comLogin'
					,params : {
						userId : this.user_id
					}
				})	
			}
		})
    }
  }
}
</script>

<style>

</style>