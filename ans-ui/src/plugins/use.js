import Vue from "vue";
import settings from "@/config/defaultSettings";

import Storage from "vue-ls";

Vue.use(Storage, settings.storageOptions);