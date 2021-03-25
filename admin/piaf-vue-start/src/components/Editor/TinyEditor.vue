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
      images_upload_url: '/api/image',
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
    }
  }
}
</script>
