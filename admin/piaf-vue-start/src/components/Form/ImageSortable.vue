<template>
  <draggable class="row icon-cards-row mb-2">
      <b-colxx xxs="6" sm="4" md="3" lg="2">
        <img :src="data.img" class="card-img responsive" :alt="data.title"/>
      </b-colxx>
    <b-colxx xxs="6" sm="4" md="3" lg="2">
      <img :src="data.img" class="card-img responsive" :alt="data.title"/>
    </b-colxx>
    <b-colxx xxs="6" sm="4" md="3" lg="2">
      <img :src="data.img" class="card-img responsive" :alt="data.title"/>
    </b-colxx>
    <b-colxx xxs="6" sm="4" md="3" lg="2">
      <img :src="data.img" class="card-img responsive" :alt="data.title"/>
    </b-colxx>
    <b-colxx xxs="6" sm="4" md="3" lg="2">
      <img :src="data.img" class="card-img responsive" :alt="data.title"/>
    </b-colxx>
  </draggable>
</template>

<script>
import Draggable from 'vuedraggable'

export default {
  name: "ImageSortable.vue",
  components: {
    'draggable': Draggable
  },
  props: ['data'],
}
</script>

<style scoped>
img {
  max-width: 150px;
  max-height: 150px;
  margin: auto;
}

</style>
