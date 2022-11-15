const ACCOUNTS = '/users';
const CALLBACK = '/callback';
// const HOST = process.env.VUE_APP_API_URL;
// export default {
//   accounts: {
//     login: type => HOST + ACCOUNTS + `/${type}` + CALLBACK,
//     profile: () => HOST + ACCOUNTS + '/mypage',
//     logout: () => HOST + ACCOUNTS + '/logout'
//   },
// };

export default {
  accounts: {
    login: type => '/api' + ACCOUNTS + `/${type}` + CALLBACK,
    profile: () => '/api' + ACCOUNTS + '/mypage',
    logout: () => '/api' + ACCOUNTS + '/logout',
    reissue: () => '/api' + ACCOUNTS + '/reissue',
    delete: () => '/api' + ACCOUNTS + '/delete'
  },
}