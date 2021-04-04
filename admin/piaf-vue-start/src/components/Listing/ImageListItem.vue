<template>
  <b-card @click.prevent="toggleItem($event,data.id)" :class="{'active' : selectedItems.includes(data.id)}" no-body>
    <div class="position-relative">
      <router-link :to="`./edit/${data.id}`" class="w-40 w-sm-100">
        <img :src="img" class="card-img-top" :alt="data.name"/>
      </router-link>
      <b-badge pill :variant="data.statusColor" class="position-absolute badge-top-left">{{ data.status }}</b-badge>
    </div>
    <b-card-body>
      <b-row>
        <b-colxx xxs="2">
          <b-form-checkbox :checked="selectedItems.includes(data.id)" class="itemCheck mb-0"/>
        </b-colxx>
        <b-colxx xxs="10" class="mb-3">
          <h6 class="mb-4 card-subtitle">{{ data.name }}</h6>
          <p class="text-muted text-small mb-0 font-weight-light card-text">색상 : {{ data.color }}</p>
          <p class="text-muted text-small mb-0 font-weight-light card-text">소재 : {{ data.material }}</p>
          <p class="text-muted text-small mb-0 font-weight-light card-text">타입 : {{ data.mountingType }}</p>
        </b-colxx>
      </b-row>
    </b-card-body>
  </b-card>
</template>

<script>
import {toThumbnail} from "@/utils/imageUtile";

export default {
  props: ['data', 'selectedItems'],
  computed: {
    img: function () {
      return toThumbnail(this.data)
    }
  },
  methods: {
    toggleItem(event, itemId) {
      this.$emit('toggle-item', event, itemId)
    }
  }
}
</script>
