<template>

  <div>
    <h4 class="lighter">
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
    </h4>

    <p>
      <router-link to="/business/course" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-arrow-left"></i>
        返回课程
      </router-link>
      &nbsp;

      <!-- Button trigger modal -->
      <button @click="add()" class="btn btn-white btn-default btn-round eye-protector-processed" style="transition: background-color 0.3s ease 0s; border-color: rgba(0, 0, 0, 0.35); color: rgb(0, 0, 0); background-color: rgb(193, 230, 198);">
        <i class="ace-icon fa fa-edit " ></i>
        新增
      </button>
      &nbsp;
      <button @click="list(1)" class="btn btn-white btn-default btn-round eye-protector-processed" style="transition: background-color 0.3s ease 0s; border-color: rgba(0, 0, 0, 0.35); color: rgb(0, 0, 0); background-color: rgb(193, 230, 198);">
        <i class="ace-icon fa fa-refresh " ></i>
        刷新
      </button>
    </p>

    <Pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8" ></Pagination>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>

        <th>ID</th>
        <th>名称</th>
        <th>操作</th>

      </tr>
      </thead>

      <tbody>

      <tr v-for="chapter in chapters">
        <td>{{ chapter.id }}</td>
        <td>{{ chapter.name }}</td>


        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              小节
            </button>&nbsp;
            <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              编辑
            </button>&nbsp;
            <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
              删除
            </button>
          </div>
        </td>
      </tr>

      </tbody>
    </table>
    <!-- Modal -->
    <div  id="form-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" >表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

              <div class="form-group">
                <label  class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" type="text" class="form-control"  placeholder="名称">
                </div>
              </div>

              <div class="form-group">
                <label  class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <p class="form-control-static" >{{course.name}}</p>
                </div>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button @click="save" type="button" class="btn btn-primary">保存</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>

import Pagination from "../../components/pagination"
export default {
  name: 'chapter',
  components: {Pagination},
  data: function () {
    return{
      chapter: {},
      course: {},
      chapters: []
    }
  },

  mounted: function () {
    // sidebar激活样式方法一
    this.$parent.activeSidebar("business-course-sidebar");
    let _this = this;
    _this.$refs.pagination.size = 10;
    let course = SessionStorage.get(SESSION_KEY_COURSE) || {};
    if (Tool.isEmpty(course)) {
      _this.$router.push("/welcome");
    }
    _this.course = course;
    _this.list(1);

  },

  methods: {
    add() {
      let _this = this;
      _this.chapter = {};
      $("#form-modal").modal("show");
    },

    edit(chapter) {
      let _this = this;
      _this.chapter = $.extend({},chapter);
      $("#form-modal").modal("show");
    },

    /**
     * 点击【查询】
     * @param page
     */

    list(page) {
      let _this = this;
      Loading.show();

      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
        page: page,
        size: _this.$refs.pagination.size,
        courseId : _this.course.id
      }).then((response) => {
        Loading.hide();

        let resp = response.data;
        _this.chapters = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      });
    },

    /**
     * 点击【保存】
     * id有值时更新，无值时插入
     *
     */
    save() {
      let _this = this;

      if (!Validator.require(_this.chapter.name, '名称')
          || !Validator.length(_this.chapter.courseId, "课程ID", 1,8)) {
        return;
      }

      _this.chapter.courseId = _this.course.id;

      Loading.show();

      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save',
        _this.chapter
      ).then((response) => {
        Loading.hide();

        let resp = response.data;
        if (resp.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功");
        } else {
          Toast.warning(resp.message);
        }

      });
    },

    /**
     * 点击【删除】
     *
     */
    del(id) {
      let _this = this;

      Confirm.show("删除大章后不可恢复，确认删除？", function () {

            Loading.show();
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/'+id,
                _this.chapter
            ).then((response) => {
              Loading.hide();
              let resp = response.data;
              if (resp.success) {
                _this.list(1);
                Toast.success("删除成功！");
              }
            })
          }

      );
    },
    /**
     * 点击【小节】
     */
    toSection(chapter) {
      let _this = this;
      SessionStorage.set(SESSION_KEY_CHAPTER, chapter);
      _this.$router.push("/business/section");
    }
  }

}
</script>