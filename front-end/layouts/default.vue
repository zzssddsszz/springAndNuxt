<template>
  <div>
    <TopPanel v-if="isShowing" @clicked="onTopPanelClose"></TopPanel>
    <TopHeader></TopHeader>
    <Menubar></Menubar>
    <nuxt />
    <Footer></Footer>
    <SiteLoader  v-if="loading" />
  </div>
</template>

<script>
import TopPanel from './TopPanel';
import TopHeader from './TopHeader';
import Menubar from './Menubar';
import Footer from './Footer';
import SiteLoader from './SiteLoder';
export default {
  components: {
    TopPanel, TopHeader, Menubar, Footer, SiteLoader
  },
  data() {
    return {
      loading: true,
      isShowing: true
    }
  },
  methods: {
    onTopPanelClose (value) {
      this.isShowing = value;
    }
  },
  watch: {
    '$route' (pathUrl){
      this.loading = true;
      setTimeout(() => { this.loading = false; }, 600);
    }
  },
  mounted(){
    setTimeout(() => { this.loading = false; }, 600);
  }
}
</script>
