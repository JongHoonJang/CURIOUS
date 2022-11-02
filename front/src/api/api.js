const HOST = process.env.VUE_APP_API_URL

const ACCOUNTS = '/users'
const CALLBACK = '/callback'

export default {
  accounts: {
    login: type => HOST + ACCOUNTS + `/${type}` + CALLBACK,
  },
}
