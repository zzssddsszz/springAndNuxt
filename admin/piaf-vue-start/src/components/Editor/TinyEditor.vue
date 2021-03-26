<template>
  <tiny-editor
    v-model="content"
    api-key="mr2p11bzs55lpw4msitqpn6xfdb7x4v5ch4oz21dprny8iy7"
    cloud-channel="5"
    id="uuid"
    :disabled=false
    :init="{
      height: 500,
      language: 'ko_KR',
      images_upload_handler: example_image_upload_handler,
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
    initial-value=""
    :inline=false
    model-events=""
    plugins=""
    tag-name="div"
    toolbar=""
    value=""
    @onChange="change"
  ></tiny-editor>
</template>
<script>
import Editor from '@tinymce/tinymce-vue';

export default {
  components: {
    "tiny-editor": Editor
  },
  data() {
    return {
      content: ""
    }
  },
  methods: {
    change(){
      this.$emit("changeContent",this.content)
    },

    example_image_upload_handler (blobInfo, success, failure, progress) {
      var xhr, formData;
      console.log("이미지 핸들러 접근")

      xhr = new XMLHttpRequest();
      xhr.withCredentials = false;
      xhr.open('POST', '/api/image');

      xhr.upload.onprogress = function (e) {
        progress(e.loaded / e.total * 100);
      };

      xhr.onload = function() {
        var json;

        if (xhr.status === 403) {
          failure('HTTP Error: ' + xhr.status, { remove: true });
          return;
        }

        if (xhr.status < 200 || xhr.status >= 300) {
          failure('HTTP Error: ' + xhr.status);
          return;
        }

        json = JSON.parse(xhr.responseText);

        if (!json || typeof json.location != 'string') {
          failure('Invalid JSON: ' + xhr.responseText);
          return;
        }

        success(json.location);
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
