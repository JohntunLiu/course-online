<template>
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="center">
              <h1>
                <i class="ace-icon fa fa-leaf green"></i>
                <!--                <span class="red"></span>-->
                <span class="grey" id="id-text2">控制台登录</span>
              </h1>
              <!--              <h4 class="blue" id="id-company-text">&copy; Company Name</h4>-->
            </div>

            <div class="space-6"></div>

            <div class="position-relative">
              <div id="login-box" class="login-box visible widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header blue lighter bigger">
                      <i class="ace-icon fa fa-coffee green"></i>
                      请输入用户名和密码
                    </h4>

                    <div class="space-6"></div>

                    <form>
                      <fieldset>
                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.loginName" type="text" class="form-control" placeholder="用户名"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                        </label>

                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.password" type="password" class="form-control" placeholder="密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                        </label>

                        <div class="space"></div>

                        <div class="clearfix">
                          <label class="inline">
                            <input v-model="remember" type="checkbox" class="ace"/>
                            <span class="lbl"> 记住我</span>
                          </label>

                          <button type="button"
                                  class="width-35 pull-right btn btn-sm btn-primary"
                                  @click="login()"
                          >
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">登录</span>
                          </button>
                        </div>

                        <div class="space-4"></div>
                      </fieldset>
                    </form>

                    <!--                    <div class="social-or-login center">
                                        <div class="social-or-login center">
                                          <span class="bigger-110">Or Login Using</span>
                                        </div>

                                        <div class="space-6"></div>

                                       <div class="social-login center">
                                          <a class="btn btn-primary">
                                            <i class="ace-icon fa fa-facebook"></i>
                                          </a>

                                          <a class="btn btn-info">
                                            <i class="ace-icon fa fa-twitter"></i>
                                          </a>

                                          <a class="btn btn-danger">
                                            <i class="ace-icon fa fa-google-plus"></i>
                                          </a>
                                        </div>-->
                  </div>

                </div><!-- /.widget-body -->
              </div><!-- /.login-box -->


            </div><!-- /.position-relative -->


          </div>
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.main-content -->
  </div><!-- /.main-container -->

</template>

<script>

  export default {
    name: "login",
    data: function () {
      return {
        user: {},
        remember: true, // 默认勾选记住我
        imageCodeToken: ""
      }
    }, mounted() {
      let _this = this;
      $('body').removeClass('no-skin');
      $('body').attr('class', 'login-layout light-login');

      let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER);
      if (rememberUser) {
        _this.user = rememberUser;
      }
    },

    methods: {

      login() {
        let _this = this;

        // let passwordShow = _this.user.password;

        let md5 = hex_md5(_this.user.password) ;
        let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER ) || {};
        if (md5 !== rememberUser.md5) {

          _this.user.password = hex_md5(_this.user.password + KEY);
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login', _this.user).then((response) => {
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            console.log("login success: " + resp.content);
            let loginUser = resp.content;
            Tool.setLoginUser(loginUser);

            if (_this.remember) {
              // 如果勾选记住我，则将用户名密码保存到本地缓存
              // 原：这里需要保存密码明文，否则登录时又会再加一层密
              // 新：这里保存密码密文，并保存密文md5，用于检测密码是否被重新输入过
              let md5 = hex_md5(_this.user.password);
              LocalStorage.set(LOCAL_KEY_REMEMBER_USER, {
                loginName: loginUser.loginName,
                // password: passwordShow,
                password: _this.user.password,
                md5: md5

              });
            } else {
              LocalStorage.set(LOCAL_KEY_REMEMBER_USER, null);
            }
            this.$router.push("/welcome");

          } else {
            Toast.warning(resp.message);
            _this.user.password = "";

          }
        },);
      }
    }
  }
</script>


