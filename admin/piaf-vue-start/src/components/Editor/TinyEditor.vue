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
      content_style:`@import url('https://fonts.googleapis.com/css2?family=Oswald&display=swap'); body { font-family: Oswald; };img {max-width: 100%;}`,
      font_formats:`Andale Mono=andale mono,times; Arial=arial,helvetica,sans-serif; Arial Black=arial black,avant garde; Book Antiqua=book antiqua,palatino; Comic Sans MS=comic sans ms,sans-serif; Courier New=courier new,courier; Georgia=georgia,palatino; Helvetica=helvetica; Impact=impact,chicago; Oswald=oswald; Symbol=symbol; Tahoma=tahoma,arial,helvetica,sans-serif; Terminal=terminal,monaco; Times New Roman=times new roman,times; Trebuchet MS=trebuchet ms,geneva; Verdana=verdana,geneva; Webdings=webdings; Wingdings=wingdings,zapf dingbats`,
      plugins: [
        'advlist autolink link image lists charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
        'table emoticons template paste help'
      ],
      toolbar: 'undo redo | fontselect | styleselect | bold italic | alignleft aligncenter alignright alignjustify | ' +
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
    value=""
    @onChange="change"
  ></tiny-editor>
</template>
<script>
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
    change(){
      this.$emit("changeContent",this.content)
    },
  }
}


</script>
<style>
iframe{
  overflow: hidden;
}
</style>
