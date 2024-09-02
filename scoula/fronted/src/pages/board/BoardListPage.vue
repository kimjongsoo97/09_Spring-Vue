  <script setup>
  import api from '@/api/boardApi'
  import {ref,reactive,computed,watch} from "vue";
  import moment from 'moment';
  import {useRouter,useRoute} from "vue-router";
  import {VueAwesomePaginate} from "vue-awesome-paginate";

  const cr=useRoute();
  const router=useRouter();

  const page=ref({});

  const articles=computed(()=> page.value.list);

  const pageRequest=reactive({
    page:parseInt(cr.query.page)||1,
    amount:parseInt(cr.query.amount)||10,
  });

  const handelPageChange = async(pageNum)=>{
    router.push({
      query:{page:pageNum,amount:pageRequest.amount},
    });
  };
  watch(cr,async (newValue)=>{
    console.log('WATCH',cr.query.page);
    pageRequest.page=parseInt(cr.query.page);
    pageRequest.amount=parseInt(cr.query.amount);
    await load(pageRequest);
  });

  const load = async (query)=>{
    try{
      page.value=await api.getList(query);
      console.log(page.value);
    }catch {}
  };
  load(pageRequest);
  </script>

<template>
  <div>
    <h1 class="mb-3"><i class="fa-solid fa-paste"></i>게시글 목록</h1>
<div class="mt-5 text-end">(총{{page.totalCount}})</div>
    <table class="table table-striped">
      <thead>
      <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 120px">작성일</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="article in articles" :key="article.no">
        <td>{{article.no}}</td>
        <td>
          <router-link :to="{name: 'board/detail',params:{no:article.no},query:cr.query}">
            {{article.title}}
          </router-link>
        </td>
        <td>{{article.writer}}</td>
        <td>{{moment(article.regDate).format('YYYY-MM-DD') }}</td>
      </tr>
      </tbody>
    </table>
    <div class="my-5 d-flex">
      <div class="flex-grow-1 text-center">
        <vue-awesome-paginate
          :total-items="page.totalCount"
          :items-per-page="pageRequest.amount"
          :max-pages-shown="5"
          :show-ending-buttons="true"
          v-model="pageRequest.page"
          @click="handelPageChange">
          <template #first-page-button><i class="fa-solid fa-backward-fast"></i></template>
          <template #prev-button><i class="fa-solid fa-caret-left"></i></template>
          <template #next-button><i class="fa-solid fa-caret-right"></i></template>
          <template #last-page-button><i class="fa-solid fa-forward-fast"></i></template>
        </vue-awesome-paginate>
      </div>
      <div>
        <router-link :to="{name:'board/create',query:cr.query}" class="btn btn-primary">
          <i class="fa-solid fa-pen-to-square"></i>글작성
        </router-link>
      </div>
    </div>
  </div>
</template>