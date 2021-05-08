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

    <div class="row">
      <div v-for="course in courses" class="col-md-4">
        <div class="thumbnail search-thumbnail">
          <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg" />
          <img v-show="course.image" class="media-object" v-bind:src="course.image" />
          <div class="caption">
            <div class="clearfix">
              <span class="pull-right label label-primary info-label">
                {{COURSE_LEVEL | optionKV(course.level)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{COURSE_CHARGE | optionKV(course.charge)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{COURSE_STATUS | optionKV(course.status)}}
              </span>
            </div>

            <h3 class="search-title">
              <a href="#" class="blue">{{ course.name }}</a>
            </h3>

<!--            <div v-for="teacher in teachers.filter(t=>{return t.id===course.teacherId})" class="profile-activity clearfix">-->
<!--              <div>-->
<!--                <img v-show="!teacher.image" class="pull-left" src="/ace/assets/images/avatars/avatar5.png">-->
<!--                <img v-show="teacher.image" class="pull-left" v-bind:src="teacher.image">-->
<!--                <a class="user" href="#"> {{teacher.name}} </a>-->
<!--                <br>-->
<!--                {{teacher.position}}-->
<!--              </div>-->
<!--            </div>-->

            <p>
              <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>&nbsp;
            </p>
            <p>{{ course.summary}}</p>

            <p>
              <span class="badge badge-info">{{course.id}}</span>
              <span class="badge badge-info">排序：{{course.sort}}</span>
              <span class="badge badge-info">{{course.time | formatSecond}}</span>
            </p>
            <p>
              <button v-on:click="toChapter(course)" class="btn btn-white btn-xs btn-info btn-round">
                大章
              </button>&nbsp;
              <button v-on:click="toContent(course)" class="btn btn-white btn-xs btn-info btn-round">
                内容
              </button>&nbsp;
              <button v-on:click="openSortModal(course)" class="btn btn-white btn-xs btn-info btn-round">
                排序
              </button>&nbsp;
              <button v-on:click="edit(course)" class="btn btn-white btn-xs btn-info btn-round">
                编辑
              </button>&nbsp;
              <button v-on:click="del(course.id)" class="btn btn-white btn-xs btn-warning btn-round">
                删除
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>

<!--    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>id</th>

            <th>名称</th>

            <th>概述</th>

            <th>时长</th>

            <th>价格（元）</th>

            <th>封面</th>

            <th>级别</th>

            <th>收费</th>

            <th>状态</th>

            <th>报名数</th>

            <th>顺序</th>



            <th>讲师</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="course in courses">
            <td>{{ course.id}}</td>
            <td>{{ course.name}}</td>
            <td>{{ course.summary}}</td>
            <td>{{ course.time}}</td>
            <td>{{ course.price}}</td>
            <td>{{ course.image}}</td>
            <td>{{ COURSE_LEVEL | optionKV(course.level)}}</td>
            <td>{{ COURSE_CHARGE | optionKV(course.charge)}}</td>
            <td>{{ COURSE_STATUS | optionKV(course.status)}}</td>
            <td>{{ course.enroll}}</td>
            <td>{{ course.sort}}</td>
            <td>{{ course.teacherId}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button @click="edit(course)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button @click="del(course.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

          </div>

        </td>
      </tr>

      </tbody>
    </table>-->
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
                <label class="col-sm-2 control-label">
                  分类
                </label>
                <div class="col-sm-10">
                  <ul id="tree" class="ztree"></ul>
                </div>
              </div>

              <div class="form-group">
                     <label class="col-sm-2 control-label">名称</label>
                     <div class="col-sm-10">
                       <input v-model="course.name" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">概述</label>
                     <div class="col-sm-10">
                       <input v-model="course.summary" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">时长</label>
                     <div class="col-sm-10">
                       <input v-model="course.time" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">价格（元）</label>
                     <div class="col-sm-10">
                       <input v-model="course.price" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">封面</label>
                     <div class="col-sm-10">
                       <input v-model="course.image" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">级别</label>
                     <div class="col-sm-10">
                       <select v-model="course.level" class="form-control">
                         <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">{{o.value}}</option>
                       </select>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">收费</label>
                     <div class="col-sm-10">
                       <select v-model="course.charge" class="form-control">
                         <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                       </select>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">状态</label>
                     <div class="col-sm-10">
                       <select v-model="course.status" class="form-control">
                         <option v-for="o in COURSE_STATUS" v-bind:value="o.key">{{o.value}}</option>
                       </select>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">报名数</label>
                     <div class="col-sm-10">
                       <input v-model="course.enroll" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">顺序</label>
                     <div class="col-sm-10">
                       <input v-model="course.sort" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">讲师</label>
                     <div class="col-sm-10">
                       <input v-model="course.teacherId" class="form-control">
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
    name: 'business-course',
    components: {Pagination},
    data: function () {
      return{
        course: {},
        courses: [],
        COURSE_LEVEL: COURSE_LEVEL,
        COURSE_CHARGE: COURSE_CHARGE,
        COURSE_STATUS: COURSE_STATUS,
        categorys: [],
        tree: {},
        saveContentLabel: "",
        sort: {
          id: "",
          oldSort: 0,
          newSort: 0
        },
        teachers: [],
      }
    },

    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("business-course-sidebar");
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.allCategory();

      _this.list(1);

    },

    methods: {
      add() {
        let _this = this;
        _this.course = {};
        $("#form-modal").modal("show");
      },

      edit(course) {
        let _this = this;
        _this.course = $.extend({},course);
        $("#form-modal").modal("show");
      },

      /**
       * 点击【查询】
       * @param page
       */

      list(page) {
        let _this = this;
        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();

          let resp = response.data;
          _this.courses = resp.content.list;
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
                || !Validator.require(_this.course.name, "名称")
                || !Validator.length(_this.course.name, "名称", 1, 50)
                || !Validator.length(_this.course.summary, "概述", 1, 2000)
                || !Validator.length(_this.course.image, "封面", 1, 100)
        ) {
          return;
        }

        let categorys = _this.tree.getCheckedNodes();
        if (Tool.isEmpty(categorys)) {
          Toast.warning("请选择分类！");
          return;
        }
        _this.course.categorys = categorys;

        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save',
                _this.course
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

        Confirm.show("删除课程后不可恢复，确认删除？", function () {

                  Loading.show();
                  _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/delete/'+id,
                          _this.course
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
       * 点击【大章】
       */
      toChapter(course) {
        let _this = this;
        SessionStorage.set(SESSION_KEY_COURSE, course);
        _this.$router.push("/business/chapter");
      },

      /**
       * 点击【内容】
       */
      toContent(course) {
        let _this = this;
        SessionStorage.set(SESSION_KEY_COURSE, course);
        _this.$router.push("/business/content");
      },

      allCategory() {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.categorys = resp.content;

          _this.initTree();
        })
      },

      initTree() {
        let _this = this;
        let setting = {
          check: {
            enable: true
          },
          data: {
            simpleData: {
              idKey: "id",
              pIdKey: "parent",
              rootPId: "00000000",
              enable: true
            }
          }
        };

        let zNodes = _this.categorys;

        _this.tree = $.fn.zTree.init($("#tree"), setting, zNodes);

        // 展开所有的节点
        // _this.tree.expandAll(true);
      },

      /**
       * 查找课程下所有分类
       * @param courseId
       */
      listCategory(courseId) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list-category/' + courseId).then((res)=>{
          Loading.hide();
          console.log("查找课程下所有分类结果：", res);
          let response = res.data;
          let categorys = response.content;

          // 勾选查询到的分类
          _this.tree.checkAllNodes(false);
          for (let i = 0; i < categorys.length; i++) {
            let node = _this.tree.getNodeByParam("id", categorys[i].categoryId);
            _this.tree.checkNode(node, true);
          }
        })
      },

      openSortModal(course) {
        let _this = this;
        _this.sort = {
          id: course.id,
          oldSort: course.sort,
          newSort: course.sort
        };
        $("#course-sort-modal").modal("show");
      },

      /**
       * 排序
       */
      updateSort() {
        let _this = this;
        if (_this.sort.newSort === _this.sort.oldSort) {
          Toast.warning("排序没有变化");
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/course/sort", _this.sort).then((res) => {
          let response = res.data;

          if (response.success) {
            Toast.success("更新排序成功");
            $("#course-sort-modal").modal("hide");
            _this.list(1);
          } else {
            Toast.error("更新排序失败");
          }
        });
      },

      allTeacher() {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/all').then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.teachers = resp.content;
        })
      },

      afterUpload(resp) {
        let _this = this;
        let image = resp.content.path;
        _this.course.image = image;
      },
    }

  }
</script>

<style scoped>
.caption h3 {
  font-size: 20px;
}

@media (max-width: 1199px) {
  .caption h3 {
    font-size: 16px;
  }
}
</style>