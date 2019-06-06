export function logout(e) {
  e.preventDefault()
  localStorage.removeItem("loginToken")
}
