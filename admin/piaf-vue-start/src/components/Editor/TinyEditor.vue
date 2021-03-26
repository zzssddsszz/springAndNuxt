<template>
  <tiny-editor
    v-model="content"
    api-key="mr2p11bzs55lpw4msitqpn6xfdb7x4v5ch4oz21dprny8iy7"
    cloud-channel="5"
    id="uuid"
    :disabled=false
    :init="{
      height: 500,
      width: 'auto',
      language: 'ko_KR',
      images_upload_handler: image_upload_handler,
      content_style:'iframe{overflow: hidden;}',
      plugins: [
        'advlist autolink link image lists charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
        'table emoticons template paste help'
      ],
      toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | ' +
      'bullist numlist outdent indent | link image | print preview media fullpage | ' +
      'forecolor backcolor emoticons | help',
      menubar: 'file edit view insert format tools table help',
    }"
    :initial-value="this.initValue"
    :inline=false
    model-events=""
    plugins=""
    tag-name="div"
    toolbar=""
    value="initValue"
    @onChange="change"
    @onInit="init"
  ></tiny-editor>
</template>
<script>
//https://usang0810.tistory.com/38
import Editor from '@tinymce/tinymce-vue';

export default {
  props:['initValue'],
  components: {
    "tiny-editor": Editor
  },
  data() {
    return {
      content: "",
    }
  },
  methods: {
    init(){
      console.log("초기값 실행 init :"+this.initValue)
      this.content = this.initValue;
    },
    change(){
      this.$emit("changeContent",this.content)
    },
    addImage(data){
      this.$emit('addImage', data);
    },
    image_upload_handler (blobInfo, success, failure, progress) {
      let xhr, formData;
      let th = this;
      xhr = new XMLHttpRequest();
      xhr.withCredentials = false;
      xhr.open('POST', '/api/image');

      xhr.upload.onprogress = function (e) {
        progress(e.loaded / e.total * 100);
      };

      xhr.onload = function() {
        var json;
        // console.log(xhr)

        if (xhr.status === 403) {
          failure('HTTP Error: ' + xhr.status, { remove: true });
          return;
        }

        if (xhr.status < 200 || xhr.status >= 300) {
          failure('HTTP Error: ' + xhr.status);
          return;
        }

        json = JSON.parse(xhr.responseText).data;

        if (!json || typeof json.location != 'string') {
          failure('Invalid JSON: ' + xhr.responseText);
          return;
        }

        success(json.location);
        th.addImage(json)
      };

      xhr.onerror = function () {
        failure('Image upload failed due to a XHR Transport error. Code: ' + xhr.status);
      };

      formData = new FormData();
      formData.append('file', blobInfo.blob(), blobInfo.filename());

      xhr.send(formData);
    }
  }
}


</script>
<style>
iframe{
  overflow: hidden;
}
</style>
