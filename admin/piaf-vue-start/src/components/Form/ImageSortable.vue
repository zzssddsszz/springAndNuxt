<template>
  <draggable class="row icon-cards-row mb-2"
             :list="images"
             @start="dragging = true"
             @end="dragging = false"
  >
    <b-card v-for="i in images" v-bind:key="i.id" class="col-sm-6 col-md-3 col-xl-2 m-1">
      <img :src="toThumbnail(i.location)" class="card-img" @error="imageError" :alt="i.title" style="width: 100%"/>
      <div>
        <b-badge pill class="position-absolute badge-bottom-left" href="#" variant="secondary"
                 @click="addToEditor(i)">O
        </b-badge>
        <b-badge pill class="position-absolute badge-bottom-right" href="#" variant="light"
                 @click="deleteImage(i.id)">X
        </b-badge>
      </div>
    </b-card>

  </draggable>
</template>

<script>
import Draggable from 'vuedraggable'

export default {
  name: "ImageSortable.vue",
  components: {
    'draggable': Draggable
  },
  props: ['images'],
  computed:{
    toThumbnail(){
      return (image) => image.replace(".jpg", ".thumbnail.jpg")
    }
  },
  data() {
    return {
      dragging: false
    }
  },
  methods: {
    deleteImage(index) {
      this.$emit('deleteMainImage', index);
    },
    addToEditor(img){
      console.log(img)
      let dom = document.createElement("img")
      dom.src = img.location;
      dom.style.width = "100%";
      tinymce.activeEditor.execCommand('mceInsertRawHTML', false, dom.outerHTML)
    },
    imageError(event) {
      let min = Math.ceil(1);
      let max = Math.floor(9);
      let num = Math.floor(Math.random() * (max - min)) + min; //최댓값은 제외, 최솟값은 포함
      event.target.src = "/assets/img/details/small-" + num + ".jpg";
    }
  }
}
</script>

<style scoped>
</style>
