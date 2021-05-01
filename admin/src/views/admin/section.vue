<template>

  <div>

    <p>

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
            <th>id</th>

            <th>标题</th>

            <th>课程</th>

            <th>大章</th>

            <th>视频</th>

            <th>时长</th>

            <th>收费</th>

        <th>顺序</th>


        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="section in sections">
          <td>{{ section.id}}</td>
          <td>{{ section.title}}</td>
          <td>{{ section.courseId}}</td>
          <td>{{ section.chapterId}}</td>
          <td>{{ section.video}}</td>
          <td>{{ section.time}}</td>
          <td>{{ CHARGE | optionKV(section.charge)}}</td>
          <td>{{ section.sort}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button @click="edit(section)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button @click="del(section.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
                    <label  class="col-sm-2 control-label">标题</label>
                    <div class="col-sm-10">
                      <input v-model="section.title" type="text" class="form-control" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label">课程</label>
                    <div class="col-sm-10">
                      <input v-model="section.courseId" type="text" class="form-control" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label">大章</label>
                    <div class="col-sm-10">
                      <input v-model="section.chapterId" type="text" class="form-control" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label">视频</label>
                    <div class="col-sm-10">
                      <input v-model="section.video" type="text" class="form-control" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label">时长</label>
                    <div class="col-sm-10">
                      <input v-model="section.time" type="text" class="form-control" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label">收费</label>
                    <div class="col-sm-10">
                      <select v-model="section.charge" class="form-control">
                        <option v-for="o in CHARGE" v-bind:value="o.key">{{o.value}}</option>
                      </select>

                    </div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label">顺序</label>
                    <div class="col-sm-10">
                      <input v-model="section.sort" type="text" class="form-control" >
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
    name: 'section',
    components: {Pagination},
    data: function () {
      return{
        section: {},
        sections: [],
        CHARGE: SECTION_CHARGE,

      }
    },

    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("business-section-sidebar");
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);

    },

    methods: {
      add() {
        let _this = this;
        _this.section = {};
        $("#form-modal").modal("show");
      },

      edit(section) {
        let _this = this;
        _this.section = $.extend({},section);
        $("#form-modal").modal("show");
      },

      /**
       * 点击【查询】
       * @param page
       */

      list(page) {
        let _this = this;
        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();

          let resp = response.data;
          _this.sections = resp.content.list;
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

        // 保存校验
        if (1 != 1
                || !Validator.require(_this.section.title, "标题")
                || !Validator.length(_this.section.title, "标题", 1, 50)
                || !Validator.length(_this.section.video, "视频", 1, 200)
        ) {
          return;
        }

        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save',
                _this.section
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

        })
      },

      /**
       * 点击【删除】
       *
       */
      del(id) {
        let _this = this;

        Confirm.show("删除小节后不可恢复，确认删除？", function () {

                  Loading.show();
                  _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/delete/'+id,
                          _this.section
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
      }
    }

  }
</script>