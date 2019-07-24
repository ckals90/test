<template>
    <div id="detailView">
		<!-- 제목, 도서 썸네일, 소개, ISBN, 저자, 출판사, 출판일, 정가, 판매가
		title, thumbnail, contents, isbn, authors, publisher, datetime, price, sale_price -->
		<h2>{{$route.params.item.title}}</h2>
		<span><img id="thumbnail"/></span><br/>
		<span class="left">{{$route.params.item.contents}}</span>
		<table class="marT20" style="width:50%;" align="center">
			<tr>
				<th>저자</th>
				<td>{{$route.params.item.authors}}</td>
			</tr>
			<tr>
				<th>출판사</th>
				<td>{{$route.params.item.publisher}}</td>
			</tr>
			<tr>
				<th>출판일</th>
				<td>{{$route.params.item.datetime}}</td>
			</tr>
			<tr>
				<th>정가</th>
				<td>{{$route.params.item.price}}</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td>{{$route.params.item.sale_price}}</td>
			</tr>
			<tr>
				<th>ISBN</th>
				<td>{{$route.params.item.isbn}}</td>
			</tr>
		</table>
		<button type="button" @click="goBack" > 뒤로가기 </button>
    </div>
</template>

<script>
export default {
	name: 'searchBookDetail',
	el: '#detailView',
	data: () => ({
	}),
	methods : {
		goBack(){
			this.$router.push({ name: 'comLogin', params: { userId:this.$route.params.userId  }})
		}
	},
	mounted(){
		$("#thumbnail").attr("src",this.$route.params.item.thumbnail);	
	}
}
</script>
 
 <style>
     #detailView { text-align : center ;}
	 .left {text-align:left;}
	 .right {text-align:right;}
	 .center {text-align:center;}
	 .marT20 {margin-top: 20px;}
 </style>
 
