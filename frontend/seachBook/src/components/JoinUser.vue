<template>
    <form id ="userJoinForm" @submit.prevent="joinUser" style="text-align:center">
		<h2>Join The Book Search System</h2>
			<table align="center">
				<tr>
					<th><label>ID</label></th>
					<td><input name="user_id" id="user_id" v-model="user_id"></td>
					<td><button type="button" @click="idDuplCheck">중복체크</button></td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<th><label>Password</label></th>
					<td><input type="password" name="user_pw" id="user_pw" v-model="user_pw"></td>
				</tr>
				<tr>
					<th><label>Password-Check</label></th>
					<input type="password" name="user_pw_chk" id="user_pw_chk" v-model="user_pw">
				</tr>
			</table>
		<button type="submit">Join</button>
	</form>
</template>

<script>
export default {
	name: 'Join',
	data: () => ({
		isDulp : false
 	}),
	methods: {
		joinUser () {
			if(this.isDulp){
				if($("#user_pw").val()!=$("#user_pw_chk").val()){
					alert("패스워드가 다릅니다.")
					return false;
				}
				if(this.user_id!=''&&this.user_pw){
					var data = new FormData();

					data.append('user_id',this.user_id);
					data.append('user_pw',this.user_pw);
					this.$http.post(
						'/com/insertUser',
						data,
						{headers: {
							'Content-Type': 'application/json'
						}}
					)
					.then((response)=>{
						var result=response.data;
						if(result=="SUCCESS"){
							alert("가입성공 ! 로그인페이지로 이동합니다.")
							this.$router.push({
								name :'loginForm'
							})
						}else{
							
						}
					})
				}else{
					alert("ID와 PW는 필수 입력값입니다.");
				}
			}else{
				alert("ID 중복체크가 필요합니다.");
			}
		},
		idDuplCheck (){
			if(this.user_id==''){
				alert("ID를 입력 해주세요.");
				return false;
			}

			this.$http.get(
				'/com/idDuplCheck',
				{params: {
					user_id: this.user_id
				}}
			)
			.then((response)=>{
				if(response.data=="SUCCESS"){
					alert("사용가능한 ID 입니다.");
					this.isDulp = true;
				}else{
					alert("중복 ID가 존재합니다.");
					this.isDulp = false;
				}
			})
		}
	}
}
</script>
 
 <style>
	
 </style>
 
