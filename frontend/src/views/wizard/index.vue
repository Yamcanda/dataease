<template>
  <div
    v-if="loading"
    v-loading="loading"
    style="position:absolute;top:55px;width: 100%;height: calc(100% - 55px);"
    class="main_outer"
  />
  <div v-else-if="homeLink">
    <iframe
      id="mobsf"
      :src="homeLink"
      frameborder="0"
      style="position:absolute;top:55px;width: 100%;height: calc(100% - 55px);"
    />
  </div>
  <el-row
    v-else
    class="main_container_outer"
  >
    <el-row class="main_container">
      <el-row class="main_content">
        <el-row class="content_top_banner">
          <div class="top_banner_content">
            <div class="hint_head">{{ $t('wizard.welcome_title') }}</div>
            <div class="hint_content">{{ $t('wizard.welcome_hint') }}</div>
          </div>
          <svg-icon
            class="top_banner_img"
            icon-class="wizard_main_bg_inner"
          />
        </el-row>
      </el-row>
    </el-row>
  </el-row>
</template>

<script>

export default {
  name: 'Wizard',
  data() {
    return {
      loading: true
    }
  },
  computed: {
    homeLink() {
      if (this.$store.getters.uiInfo && this.$store.getters.uiInfo['ui.homeLink'] && this.$store.getters.uiInfo['ui.homeLink'].paramValue) {
        return this.$store.getters.uiInfo['ui.homeLink'].paramValue
      }
      return null
    }
  },
  mounted() {
    setTimeout(() => {
      this.loading = false
    }, 1000)
  }
}

</script>

<style lang="scss" scoped>
.main_outer {
  background-color: var(--MainBG, #f5f6f7)
}

.main_container {
  min-width: 1250px;
  padding: 0 24px 0 24px;
  overflow: auto;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  background-size: 100% 444px !important;
  background: url('../../assets/wizard_main_bg.png') no-repeat;
}

.main_content {
  width: 1200px;
}

.content_top_banner {
  color: var(--ContentBG, #FFFFFF);
  position: relative;
  width: 100%;
  height: 230px;
}

.top_banner_content {
  position: absolute;
  top: 62px;
  height: 230px;
}

.top_banner_img {
  position: absolute;
  width: 520px;
  height: 230px;
  top: 0;
  right: 50px;
}

.top_banner_card {
  position: relative;
  width: 100%;
  height: 214px;
}

.hint_head {
  line-height: 48px;
  font-weight: 600;
  font-size: 48px;
}

.hint_content {
  margin-top: 12px;
  line-height: 26px;
  font-weight: 400;
  font-size: 18px;
}

.content_middle {
  height: 290px;
  width: 100%;
  margin-top: 24px;
}

.content_middle_left {
  float: left;
  width: 792px;
  height: 290px;
  padding: 24px;
  border-radius: 4px;
  background-color: var(--ContentBG, #FFFFFF);
}

.content_middle_title {
  float: left;
  font-style: normal;
  font-weight: 500;
  font-size: 20px;
  line-height: 38px;
  color: var(--TextPrimary, #1F2329);
}

.content_middle_more {
  float: right;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 22px;
  color: #646A73;
  border-radius: 4px;
  height: 26px;
  padding: 2px;

  &:hover {
    background: rgba(31, 35, 41, 0.1);
    cursor: pointer;
  }
}

.content_middle_more i:hover {
  background: none;
}

.content_middle_more a:hover {
  background: none;
}

.content_middle_right {
  float: left;
  height: 290px;
  width: 384px;
  margin-left: 24px;
  padding: 24px;
  border-radius: 4px;
  background-color: var(--ContentBG, #FFFFFF);
}

.content_middle_left {
  float: left;
  width: 792px;
  height: 290px;
  padding: 24px;
  border-radius: 4px;
  background-color: var(--ContentBG, #FFFFFF);
}

.li-custom {
  margin-top: 16px;
  font-weight: 400;
  font-size: 14px;
  line-height: 22px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  list-style-type: disc;
  list-style-position: inside;
  border-radius: 4px;
  padding-left: 12px;
  margin-left: -12px;

  &:hover {
    background: rgba(31, 35, 41, 0.1);
    cursor: pointer;
    color: #3370FF !important;

    .li-a {
      color: #3370FF !important;
    }
  }
}

.li-custom a:hover {
  background: none;
}

.li-a {
  color: var(--TextPrimary, #1F2329);
}

.ul-custom {
  padding-inline-start: 0px;
  color: #8F959E;
}

.content_bottom {
  width: 100%;
  height: 208px;
}

.content_bottom_contact {
  float: left;
  margin-left: 278px;
  width: 300px;
  margin-top: 40px;
}

.contact_title {
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 22px;
  color: var(--TextPrimary, #1F2329);
  margin-bottom: 16px;
}

.contact_content {
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 22px;
  color: #646A73;
  margin-top: 8px;
}

.contact_content a:hover {
  color: #3370FF;
}

.content_bottom_qr_code {
  width: 400px;
  float: right;
  text-align: right;
  margin-right: 180px;
  margin-top: 40px;
}

.contact_title_qr {
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 20px;
  text-align: center;
  color: #646A73;
  margin-bottom: 8px;
}

.contact_wechat_train {
  width: 100px;
  float: right;
}

.contact_wechat_train_img {
  box-sizing: border-box;
  width: 100px;
  height: 100px;
  border: 2px solid #FFFFFF;
}

.contact_wechat_official {
  width: 100px;
  float: right;
  margin-right: 40px;
}

.contact_wechat_official_img {
  box-sizing: border-box;
  width: 100px;
  height: 100px;
  border: 2px solid #FFFFFF;
}

.contact_wechat_group {
  width: 100px;
  float: right;
  margin-right: 40px;
}

.contact_wechat_group_img {
  box-sizing: border-box;
  width: 100px;
  height: 100px;
  border: 2px solid #FFFFFF;
}

.main_container_outer {
  width: 100%;
  height: calc(100vh - 56px);
  background-color: var(--MainBG, #f5f6f7);
  overflow: auto;
}

</style>
