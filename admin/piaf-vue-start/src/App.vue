<template>
  <div class="h-100">
    <router-view />
    <color-switcher />
  </div>
</template>

<script>
import ColorSwitcher from "./components/Common/ColorSwitcher";
import axios from "axios";

import { getDirection } from "./utils";

export default {
  components: {
    "color-switcher": ColorSwitcher
  },
  beforeMount() {
    const direction = getDirection();
    if (direction.isRtl) {
      document.body.classList.add("rtl");
      document.dir = "rtl";
      document.body.classList.remove("ltr");
    } else {
      document.body.classList.add("ltr");
      document.dir = "ltr";
      document.body.classList.remove("rtl");
    }

    if (this.$store.getters.currentUser){
      axios.get("me").then((data)=>{

      }).catch((data)=>{
        console.log("로컬 스토리지에 회원정보가 있지만 서버 세션이 종료되어 자동 로그아웃됩니다.")
        this.$store.dispatch("signOut");
        this.$router.push("/user/login")
      })
    }



  }
};
</script>
