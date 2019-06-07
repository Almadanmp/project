import React, {Component} from 'react';
import TextInput from './TextInput';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {logInUser} from './sessionActions';

export class LogInPage extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {credentials: {username: '', password: ''}, collapse: false, loggedIn: 'not logged'}
    this.onChange = this.onChange.bind(this);
    this.onSave = this.onSave.bind(this);
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  onChange(event) {
    const field = event.target.name;
    const credentials = this.state.credentials;
    credentials[field] = event.target.value;
    this.setState({credentials: credentials})
  }


  onSave = async event => {
    event.preventDefault();
    logInUser(this.state.credentials.username, this.state.credentials.password)
      .then(res => {
        //If the loggin is sucessfull the user gets redirected to its home page
        if (res.status === 200) {
          this.setState({loggedIn: 'loggedIn'})
          this.props.history.replace('/about');
        }
      })
      .catch(err=>{
        console.log("CATCH PAGINA LOGIN")
        this.setState({loggedIn: 'wrongPass'})
        return console.log(err);
      }

  )
  };

  render() {
    const {credentials} = this.state;
    console.log(this.state.loggedIn)
    console.log("Devia estar a NOT")
    if ((this.state.loggedIn.indexOf("not") != -1)) {
      return (
        <>
          <style>{'body { background-color: white; }'}</style>
          <header>
            <img src="https://imgur.com/rf2xy6y.png" className="flex-logo"/>
          </header>
          <div className="first">
            <div className="div">

              <form>
                <TextInput
                  color="#e4e7ea"
                  name="username"
                  label="username"
                  type="text"
                  size="2"
                  value={credentials.username}
                  onChange={this.onChange}/>

                <TextInput
                  background-color="#00000000"
                  name="password"
                  label="password"
                  type="password"
                  size="2"
                  value={credentials.password}
                  onChange={this.onChange}/>

                <button
                  type="submit"
                  className="btn btn-primary"
                  onClick={this.onSave}> Log in
                </button>
                {" "}
              </form>
            </div>
          </div>

          <div className="second_div">
            <div className="second">
              <img src="https://imgur.com/2dNT9M2.png" className="flex-item"/>
            </div>
          </div>
        </>
      );
    } else {
      return (
        <>
          <style>{'body { background-color: white; }'}</style>
          <header>
            <img src="https://imgur.com/rf2xy6y.png" className="flex-logo"/>
          </header>
          <div className="first">
            <div className="div">
              <form>
                <TextInput
                  color="#e4e7ea"
                  name="username"
                  label="username"
                  type="text"
                  size="2"
                  value={credentials.username}
                  onChange={this.onChange}/>

                <TextInput
                  background-color="#00000000"
                  name="password"
                  label="password"
                  type="password"
                  size="2"
                  value={credentials.password}
                  onChange={this.onChange}/>

                <button
                  type="submit"
                  className="btn btn-primary"
                  onClick={this.onSave}> Log in
                </button>
                {" "}
              </form>
              <br/>
              <div className="help-block">Wrong Password or Username</div>
            </div>
          </div>

          <div className="second_div">
            <div className="second">
              <img src="https://imgur.com/2dNT9M2.png" className="flex-item"/>
            </div>
          </div>
        </>
      );
    }
  }
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(logInUser, dispatch)
  };
}

export default connect(null, mapDispatchToProps)(LogInPage);
