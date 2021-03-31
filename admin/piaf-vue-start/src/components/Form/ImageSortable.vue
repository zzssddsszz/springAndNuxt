<template>
  <draggable class="row icon-cards-row mb-2"
             :list="images"
             @change="change"
             @start="dragging = true"
             @end="dragging = false"
  >
    <b-card v-for="i in images" v-bind:key="i.id" class="col-sm-6 col-md-3 col-xl-2 m-1">
        <img :src="i.location" class="card-img" @error="imageError" :alt="i.title" style="width: 100%"/>
<!--      <b-button
        class=""
      >삭제</b-button>-->
      <b-badge pill class="position-absolute badge-bottom-right" href="#" variant="light"
               @click="deleteImage(i.id)"
      >X</b-badge>


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
  data() {
    return {
      dragging: false
    }
  },
  methods: {
    change(e) {
        this.$emit('changeIndex',e.moved.oldIndex,e.moved.newIndex);
    },
    deleteImage(index){
      this.$emit('deleteMainImage',index);
    },
    imageError(event){
      event.target.src = "/assets/img/cards/thumb-1.jpg";
    }
  }
}
</script>

<style scoped>
</style>
