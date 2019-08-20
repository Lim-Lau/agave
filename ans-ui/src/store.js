import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    token: {
      mutations: {
        login(state, token) {
          state.token = token;
          state.time = 0;
        },
        captchaCutDown(state, time) {
          state.time = time;
        },
        logout(state) {
          state.token = undefined;
        }
      }
    },
    user: {
      mutations: {
        save(state, user) {
          state.loginUser = user;
        },
        remove(state) {
          state.loginUser = {
            name: "",
            id: 0,
            code: "",
            mobile: "",
            roles: [],
            permissions: []
          };
        },
        updateAvatar(state, key) {
          state.loginUser.headKey = key;
        }
      },
      getters: {
        hasRole: state => role => {
          if (!role || role == null) return true;
          if (
            state.loginUser &&
            state.loginUser.type &&
            state.loginUser.type.value === "SU"
          )
            return true;
          return false;
        },
        hasPermission: state => permission => {
          if (!permission || permission == null) return true;
          let permissions = [];
          for (var key in state.loginUser.permissions) {
            permissions = permissions.concat(state.loginUser.permissions[key]);
          }
          permissions = Array.from(new Set(permissions));
          return permissions.filter(p => p === permission).length > 0;
        }
      },
      state: {
        loginUser: {
          name: "test",
          id: 0,
          logo: "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1525918123&di=8f8a2c459b0547b125087dde1068ddf9&src=http://img.zcool.cn/community/01786557e4a6fa0000018c1bf080ca.png",
          mobile: "",
          code: "",
          roles: [],
          permissions: [],
          branchs: []
        },
        token: ""
      }
    },
    lang: {
      state: {
        language: "en"
      },
      mutations: {
        setLanguage(state, lang) {
          state.language = lang;
        }
      }
    },
    codebooks: {
      state: {
        codebooks: []
      },
      mutations: {
        cacheCodebooks(state, codebooks) {
          state.codebooks = codebooks;
        }
      },
      getters: {
        codebooks: state => {
          return state.codebooks;
        }
      }
    }
  },
  strict: process.env.NODE_ENV !== "production",
  plugins: [createPersistedState()]
});