<template>
  <draggable class="row icon-cards-row mb-2">
    <b-card v-for="i in images" v-bind:key="i.id" class="col-sm-6 col-md-3 col-xl-2 m-1">
        <img :src="i.location" class="card-img " :alt="i.title" style="width: 100%"/>
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
}
</script>

<style scoped>
</style>
