<template lang="pug">
  v-app(dark)
    v-main
      v-app-bar
        v-app-bar-nav-icon(@click.stop="drawer = !drawer")
        v-toolbar-title Service oriented architecture
      v-container
        v-navigation-drawer(v-model="drawer" absolute bottom temporary)
          v-list(nav dense)
            v-list-item-group(v-model="group")
              v-list-item(to="/") lab_1
              v-list-item(to="/lab_2") lab_2
        Nuxt
</template>

<script>
export default {
  data() {
    return {
      drawer: false,
      group: null
    };
  },
  watch: {
    group() {
      this.drawer = false;
    }
  }
};
</script>
