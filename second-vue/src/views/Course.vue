<template>
  <div>
    <div style="margin-top:50px;">
      <el-input v-model="courseName" placeholder="请输入课程名" style="width:80%" clearable></el-input>
      <el-button type="primary" style="margin-left: 10px;" @click="searchCourseByName">搜索</el-button>
      <el-button type="primary" style="margin-left:10px;margin-bottom: 20px;" @click="showDialog">新增课程</el-button>
    </div>
    <div>
      <el-table
              @selection-change="handleSelectionChange"
              :data="courses"
              border>
        <el-table-column
                type="selection"
                width="55">
        </el-table-column>
        <el-table-column
            prop="courseId"
            label="课程名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseTeacher"
            label="任课老师"
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="课程时间"
            width="180">
        </el-table-column>
        <el-table-column
                prop="uid"
                label="上课地址"
                width="180">
        </el-table-column>

        <el-table-column
                label="操作">
                align="left">
          <template slot-scope="scope">
            <el-button
                    size="mini"
                    @click="handleEdit(scope.row)">编辑</el-button>
            <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="danger" size="mini" style="margin-top: 12px" :disabled="multipleSelection.length === 0" @click="deleteCourses">批量删除</el-button>
    </div>
    <el-dialog :title="dialogTitle[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form :model="form" :rules="rules" ref="course">
        <el-form-item label="课程名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="选择任课老师" :label-width="formLabelWidth" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择" style="width: 100%">
            <el-option
                    v-for="item in options"
                    :key="item.value"
                    :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程时间" :label-width="formLabelWidth" prop="age">
          <el-input v-model.number="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="上课地点" :label-width="formLabelWidth" prop="age">
          <el-input v-model.number="form.age" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitCourse">确 定</el-button>
      </div>
    </el-dialog>
    <el-upload
            style="margin-top:20px;"
            action="/upload"
            multiple
            :on-success="handleSuccess"
            :limit="3"
            :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
  </div>
</template>

<script>
// @ is an alias to /src


export default {
  name: 'Home',
  components: {
  },
  data () {
    return {
      fileList: [],
      courseName: '',
      multipleSelection:[],
      dialogTitle: {
        addCourse: "新增课程",
        editCourse: "编辑课程"
      },
      dialogStatus: "",
      rules: {
        name: [
          { required: true, message: '请输入课程名', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择任课老师', trigger: 'change' }
        ],
        age: [
          { required: true, message: '老师不能为空'},
          // { type: 'number', message: '年龄必须为数字值'}
        ]
      },
      options: [{
        value: '1',
        label: '徐老师'
      }, {
        value: '2',
        label: '老师徐'
      }],
      msg: '',
      courses:[],
      search: '',
      dialogFormVisible: false,
      form: {
        courseId: '',
        courseTeacher:'',
        courseName: '',
        courseDiscription:'',
        courseSemester:'',
        courseAddress:'',
      },
      formLabelWidth: '120px',
    }
  },
  created() {
    this.findAllCourses();
  },
  watch:{
    courseName () {
        this.findAllCourses();
    }
  },
  methods: {
    // deleteFile (file) {
    //   console.log(file)
    // },
    handleSuccess (file) {
      const filename = file.name;
      this.$message({
        message: '文件上传成功！',
        type: 'success'
      });
    },
    searchCourseByName () {
        if (this.courseName !== '') {
            this.$axios.get("/course/findCourseByName?name="+this.courseName).then( (resp)=> {
                this.courses = resp.data;
            }).catch( (error)=>{
                this.$message({
                    type: 'error',
                    message: "查询失败，原因是"+error.data.message
                });
            })
        }
    },
    deleteCourses () {
      this.$confirm('此操作将永久删除课程, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = '?';
        this.multipleSelection.forEach( (item)=>{
          ids += 'ids='+item.uid+'&'
        });
        this.$axios.post("/course/deleteCoursesByIds"+ids).then( (resp)=> {
          if (resp) {
            this.findAllCourses();
            this.$message.success("删除成功！");
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },
    submitCourse () {
      if (this.dialogStatus === 'addCourse'){
        this.addCourse();
      } else if(this.dialogStatus === "editCourse") {
        this.editCourse();
      }
    },
    findAllCourses () {
      this.$axios.get("/course/findAllCourses")
              .then((res) => {
                this.courses = res.data
              })
              .catch(error => {
                this.$message({
                  type: 'error',
                  message: "查询失败，原因是"+error.data.message
                });
              });
    },
    handleEdit (row) {
      this.dialogFormVisible = true;
      this.dialogStatus = "editCourse";
      this.form.uid = row.uid;
      this.form.courseId = row.courseId;
      this.form.courseTeacher = row.courseTeacher;
      this.form.courseName = row.courseName;
      this.form.courseDiscription = row.courseDiscription;
      this.form.courseSemester = row.courseSemester;
      this.form.courseAddress = row.courseAddress;
    },
    handleDelete (row) {
      this.$confirm('此操作将永久删除该课程, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/course/deleteCourse?uid="+row.uid).then ((resp)=>{
          this.$message.success("删除成功!")
          this.findAllCourses();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    showDialog () {
      this.form.uid = '';
      this.form.courseId = '';
      this.form.courseTeacher = '';
      this.form.courseName = '';
      this.form.courseDiscription = '';
      this.form.courseSemester = '';
      this.form.courseAddress = '';
      this.dialogFormVisible = true;
      this.dialogStatus = "addCourse";
    },
    addCourse () {
      this.$refs.course.validate((valid) => {
        if (valid) {
          this.$axios.post("/course/addCourse",this.form).then( (resp)=>{
            if (resp) {
              this.dialogFormVisible = false;
              this.$message.success("添加成功！");
              this.findAllCourses();
            }
          }).catch((error)=>{
            this.$message({
              message: '添加数据失败，原因是'+error.data.message,
              type: 'error'
            })
          })
        } else {
          this.$message({
            message: '请输入所有字段',
            type: 'error'
          })
          return false;
        }
      });
    },
    editCourse () {
      this.$axios.post("/course/updateCourse",this.form).then( (resp)=>{
        this.dialogFormVisible = false;
        this.$message.success("修改成功!");
        this.findAllCourses();
      }).catch( (error)=> {
        this.$message({
          message: '数据更新失败，原因是'+error.data.message,
          type: 'error'
        })
      })
    }
  }
}
</script>
<style scoped>
  .table {
    margin: 0 auto;
    clear: both;
  }
</style>
