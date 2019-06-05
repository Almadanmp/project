import React, { Component } from 'react';


class US610GetTemperature extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: 0,
    }
  }
  componentDidMount() {
    console.log(this.props);
    fetch("http://localhost:9898/roomMonitoring/dayMaxTemperature/"+this.props.roomID+"?initialDate="+this.props.day)
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log)
  }


  render() {
    return (
      <div>
        <h6 key={this.state.item}>The maximum temperature
         was {this.state.item}ºC</h6>
      </div>
    );
  }

}

export default US610GetTemperature;
