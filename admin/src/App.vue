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
															<input type="text" class="form-control" placeholder="Username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                        </label>

                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                        </label>

                        <div class="space"></div>

                        <div class="clearfix">
                          <label class="inline">
                            <input type="checkbox" class="ace"/>
                            <span class="lbl"> 记住我</span>
                          </label>

                          <button type="button" class="width-35 pull-right btn btn-sm btn-primary">
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

$('body').attr('class', 'login-layout light-login');
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
