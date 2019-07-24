<template>
	<span id="searchFrom">
		<select id="searchDiv" name="searchDiv" v-model="searchDiv" style="text-align:center">
			<option value="total">전체</option>
			<option value="title">제목</option>
			<option value="isbn">ISBN</option>
			<option value="publisher">출판사</option>
			<option value="person">인명</option>
		</select>
		<input type="text" id="searchKeyword" v-model="searchKeyword">
		<button type="button" class="btn btn-primary" @click="search">검색</button>
		<div style="width:100%">
			<div style="width:60%;float:left;">
				<div v-if="page!=0" style="text-align:center">
					<button :disabled="page < 2" @click="prevPage">
						이전
					</button>
					<span>{{ page }} / {{ pageCount }} </span>
					<button :disabled="page >= pageCount" @click="nextPage" >
						다음
					</button>
				</div>
				<div id="searching-value"></div>
				<div v-if="page!=0" style="text-align:center">
					<button :disabled="page < 2" @click="prevPage" >
						이전
					</button>
					<span>{{ page }} / {{ pageCount }} </span>
					<button :disabled="page >= pageCount" @click="nextPage" >
						다음
					</button>
				</div>
			</div>
			<div style="width:25%;float:left;">
				<h2>검색어 Top 10</h2>
				<table>
					<thead>
						<th>순위</th>
						<th>검색어</th>
						<th>검색건수</th>
					</thead>
					<tbody id="ranking">
					</tbody>
				</table>
			</div>
			<div style="width:25%;float:left;">
				<h2>나의 검색어 History</h2>
				<table>
					<thead>
						<th>검색어</th>
						<th>검색시간</th>
					</thead>
					<tbody id="history">
					</tbody>
				</table>
			</div>
		</div>
</template>

<script>
export default {
	name: 'searchBookMain',
	el: '#searchForm',
	data: () => ({
		searchDiv:'total',
		searchKeyword:'',
		item :{},
		page : 0 ,
		pageCount :0,
		keyword : '',
		update : 0 ,
		userId : ''
	}),
	methods : {
		search() {
			this.userId=this.$route.params.userId
			if(this.keyword!=this.searchKeyword){
				this.keyword = this.searchKeyword
				this.page=0
				this.pageCount=0
				this.update=0
			} 

			this.$http.get(
				'/com/doSearchBook',
				{
					params : {
						searchDiv : this.searchDiv,
						searchKeyword : this.searchKeyword,
						userId : this.userId,
						page : this.page==0?1:this.page,
						update : this.update
					}
				}
			)
			.then((response)=>{
				var contents =response.data.searchResult.documents;
				var meta =response.data.searchResult.meta; 
				this.setSearchingValue(contents);
				if(meta.total_count!=0){
					this.page= this.page==0?1:this.page
					this.setPage(meta);
				}
				this.setTopRank(response.data.top)
				this.setHistory(response.data.history)
			})
		},
		setSearchingValue(data){
			this.item=data

			var html ="";
			html+="<table>";
			html+="    <colgroup>";
			html+="        <col width=\"20%\">";
			html+="        <col width=\"*\">";
			html+="    </colgroup>"
			html+="    <tbody>";
			
			$.each(data,function(i,item){
				html+="        <tr>";
				html+="            <td rowspan=2>";
				html+="                <img src=\""+item.thumbnail+"\">";
				html+="            </td>";
				html+="            <td>";
				html+="                <span class=\"left title\"><a href=\"javascript:\" _idx=\""+i+"\">"+item.title+"</a></span>";
				html+="            </td>";
				html+="        </tr>";
				html+="        <tr>";
				html+="            <td>";
				html+="                <span class=\"left marL10\">"+item.authors+" 지음</span>";
				html+="                <span class=\"left marL10\">"+item.publisher+" 출판</span>";
				html+="                <span class=\"left marL10\"> ISBN "+item.isbn+"</span>";
				html+="            </td>";
				html+="        </tr>";
				})
			$("#searching-value").html(html);
		},setPage(meta){
			//is_end":false,"pageable_count":16,"total_count":16
			this.pageCount =  Math.floor(meta.total_count/(10 - 1)) + 1;
		},prevPage(){
			this.page--
			this.update=1
			this.search()
		},nextPage(){
			this.page++
			this.update=1
			this.search()
		},setTopRank(data){
			var html="";
			$.each(data,function(i,item){
				html+="<tr>"
				html+="    <td class='title'>"+(i+1)+"</td>"
				html+="    <td class='title'>"+item.KEYWORD+"</td>"
				html+="    <td class='title'>"+item.CNT+"</td>"
				html+="</tr>"
			});
			$("#ranking").html(html);
		},setHistory(data){
			var html="";
			$.each(data,function(i,item){
				html+="<tr>"
				html+="    <td class='title'>"+item.KEYWORD+"</td>"
				html+="    <td class='title'>"+item.REG_DATE+"</td>"
				html+="</tr>"
			});
			$("#history").html(html);
		}
	},
	updated(){
		const $this=this
		$(document).on("click","a",function(){
			var idx=$(this).attr("_idx")
			$this.$router.push({
				name :'searchBookDetail',
				params: {
					item : $this.item[idx],
					userId : $this.userId
				}
			})	
		});
	}
}

</script>
 
 <style>
	.left {text-align: left;}
	.title {font-size: 15px;}
	.marL10 {margin-left : 10px;}
	.marR10 {margin-right : 10px;}
	span {font-size: 11px;}
 </style>
 
