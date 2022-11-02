const HOST = process.env.VUE_APP_API_URL

const ACCOUNTS = '/users'
const CALLBACK = '/callback'
export default {
  accounts: {
    login: type => HOST + ACCOUNTS + `/${type}` + CALLBACK,
    profile: () => HOST + ACCOUNTS + '/mypage',
    logout: () => HOST + ACCOUNTS + '/logout'
  },
}

// export default {
//   accounts: {
//     login: type => '/api' + ACCOUNTS + `/${type}` + CALLBACK,
//     profile: () => '/api' + ACCOUNTS + '/mypage',
//     logout: () => '/api' + ACCOUNTS + '/logout'
//   },
// }