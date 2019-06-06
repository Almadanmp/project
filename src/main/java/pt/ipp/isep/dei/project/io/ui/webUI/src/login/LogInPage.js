import React, {Component} from 'react';
import TextInput from './TextInput';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {logInUser} from './sessionActions';


export class LogInPage extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {credentials: {username: '', password: ''}, collapse: false}
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
          this.props.history.replace('/about');
        }
      })
      .catch(error => {
        throw(error);
      });
  };

  render() {
    const {credentials} = this.state;
    return (
      <>
        <img src="https://i.imgur.com/WR4v97o.png" className="flex-logo"/>

        <div className="first">
          <div className="div">

            <form>
              <TextInput
                name="username"
                label="username"
                type="text"
                size="2"
                value={credentials.username}
                onChange={this.onChange}/>

              <TextInput
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
  }
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(logInUser, dispatch)
  };
}

export default connect(null, mapDispatchToProps)(LogInPage);
