const ACCOUNTS = '/users';
const CALLBACK = '/callback';
const HOST = process.env.VUE_APP_API_URL;
export default {
  accounts: {
    login: type => HOST + ACCOUNTS + `/${type}` + CALLBACK,
    profile: () => HOST + ACCOUNTS + '/mypage',
    logout: () => HOST + ACCOUNTS + '/logout',
    reissue: () => HOST + ACCOUNTS + '/reissue',
    delete: () => HOST + ACCOUNTS + '/delete'
  },
};
