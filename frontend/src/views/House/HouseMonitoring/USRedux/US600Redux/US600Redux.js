import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchTemp } from './Actions';

class US600Redux extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchTemp();
  }

  render() {

    const { loading,temp } = this.props;
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    }
       else {
         if ({temp}===0) {
           return(
             <div>
               <h4>There are no data.
               </h4>
             </div>
           )
         }
         else{
            return (
              <div>
                <h4 key={temp}>
                  Current Temperature: {temp} ºC
                </h4>
              </div>
          );

      }
      }
  }


}

const mapStateToProps = (state) => {
  return {
      loading: state.Reducers600.loading,
      temp: state.Reducers600.temp,
      error: state.Reducers600.error
    }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchTemp: () => {
      dispatch(fetchTemp())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US600Redux);
